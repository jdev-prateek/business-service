package com.jdev.business_service.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.business_service.enums.ResponseStatus;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiResponse {
    @JsonIgnore
    private List<String> globalErrors;
    private Map<String, String> fieldErrors;
    private ResponseStatus status;

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public void addGlobalErrors(String error) {
        if(null == this.globalErrors){
            this.globalErrors = new ArrayList<>();
        }

        this.globalErrors.add(error);
    }

    @JsonIgnore
    public boolean isSuccess(){
        return ResponseStatus.SUCCESS.equals(status);
    }

    public boolean hasErrors(){
        return CollectionUtils.isNotEmpty(globalErrors);
    }
}
