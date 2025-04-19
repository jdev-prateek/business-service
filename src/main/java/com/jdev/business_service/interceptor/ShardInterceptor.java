package com.jdev.business_service.interceptor;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jdev.business_service.config.ShardContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class ShardInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ShardInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if("POST".equals(request.getMethod()) && request.getRequestURI().equals("/business")) {
            UUID timeOrderedEpoch = UuidCreator.getTimeOrderedEpoch();
            ShardContextHolder.setBusinessIdHolder(timeOrderedEpoch);
            log.info("Adding UUID:{} to ShardContextHolder", timeOrderedEpoch);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Clear ShardContextHolder");
        ShardContextHolder.clear();
    }
}
