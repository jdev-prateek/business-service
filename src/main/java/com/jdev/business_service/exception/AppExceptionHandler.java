package com.jdev.business_service.exception;

import com.jdev.business_service.enums.ResponseStatus;
import com.jdev.business_service.vo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> exceptionMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                WebRequest request) {
        log.error("{}",exception.getBindingResult().getFieldErrors(), exception);
        HashMap<String, String> payload = new HashMap<>();

        ApiResponse apiResponse = new ApiResponse();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            payload.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiResponse.setFieldErrors(payload);
        apiResponse.setStatus(ResponseStatus.FAILURE);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @ExceptionHandler({NoResourceFoundException.class, EntityNotFound.class})
    public ResponseEntity<ApiResponse> notFoundHandler(Exception exception, WebRequest request) {
        log.error("",exception);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.addGlobalErrors(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handler(Exception exception, WebRequest request) {
        log.error("",exception);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ResponseStatus.FAILURE);
        apiResponse.addGlobalErrors(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiResponse);
    }
}
