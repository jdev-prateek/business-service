package com.jdev.business_service.vo.ui;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class AddBusinessVO {
    @Valid
    @NotNull(message = "payload must not be null")
    private Payload payload;

    public static class Payload{
        @Valid
        @NotNull(message = "business name must not be null")
        private String name;

        @Valid
        @NotNull(message = "gstin name must not be null")
        private String gstin;

        @Valid
        @NotNull(message = "preferredLang must not be null")
        private String preferredLang;

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
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
