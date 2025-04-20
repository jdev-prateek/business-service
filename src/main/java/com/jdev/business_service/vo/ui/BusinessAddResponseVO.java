package com.jdev.business_service.vo.ui;

import com.jdev.business_service.vo.ServiceResponse;

import java.util.UUID;

public class BusinessAddResponseVO extends ServiceResponse {
    private UUID id;
    private String name;
    private String gstin;
    private String preferredLang;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    @Override
    public String toString() {
        return "BusinessResponseVO{" +
                "id=" + id +
                ", name=" + name +
                ", gstin=" + gstin +
                '}';
    }
}
