package com.jdev.business_service.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.f4b6a3.uuid.UuidCreator;
import com.jdev.business_service.config.ShardContextHolder;
import com.jdev.business_service.constants.ErrorKeyConstants;
import com.jdev.business_service.enums.ResponseStatus;
import com.jdev.business_service.utils.wrapper.CachedBodyHttpServletRequest;
import com.jdev.business_service.vo.ServiceResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ShardContextFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ShardContextFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try{
            if(businessIDExtractionRequired(httpRequest)){
                CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest((HttpServletRequest) request);
                String body = wrappedRequest.getCachedBodyAsString();
                log.info("Request Body: {}", body);

                String businessId = extractBusinessIdFromBody(body);

                if (businessId != null) {
                    ShardContextHolder.setBusinessIdHolder(UUID.fromString(businessId));
                    log.info("Business ID set in ShardContextHolder: {}", businessId);
                }

                chain.doFilter(wrappedRequest, response);
            }

            else if(businessIDCreationRequired(httpRequest)){
                UUID timeOrderedEpoch = UuidCreator.getTimeOrderedEpoch();
                ShardContextHolder.setBusinessIdHolder(timeOrderedEpoch);

                chain.doFilter(request, response);
                log.info("Business ID set in ShardContextHolder: {}", timeOrderedEpoch);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e){
            log.error("Error in ShardContextFilter: {}", e.getMessage(), e);
            sendError((HttpServletResponse) response, HttpServletResponse.SC_BAD_REQUEST, "Internal server error");
        } finally {
            ShardContextHolder.clear();
            log.info("ShardContextHolder cleared");
        }

    }

    @Override
    public void destroy() {
    }

    private String extractBusinessIdFromBody(String body) {
        try {
            JsonNode jsonBody = objectMapper.readTree(body);
            return jsonBody.path("payload").path("businessId").asText();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean businessIDExtractionRequired(HttpServletRequest httpRequest){
        if("POST".equals(httpRequest.getMethod()) && "/customers".equals(httpRequest.getRequestURI())){
            return true;
        }

        return false;
    }

    private boolean businessIDCreationRequired(HttpServletRequest httpRequest){
        if("POST".equals(httpRequest.getMethod()) && "/business".equals(httpRequest.getRequestURI())){
            return true;
        }

        return false;
    }

    private void sendError(HttpServletResponse response, int status, String message) throws IOException {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(ResponseStatus.FAILURE);
        serviceResponse.setErrorKey(ErrorKeyConstants.BUSINESS_ID_INVALID);
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ErrorKeyConstants.BUSINESS_ID_INVALID_DESC);
        serviceResponse.setErrorMap(errorMap);
        response.setStatus(status);
        response.setContentType("application/json");

        String json = objectMapper.writeValueAsString(serviceResponse);
        response.getWriter().write(json);
    }

}