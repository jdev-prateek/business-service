package com.jdev.business_service.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public class AddHSNVO {
    @Valid
    @NotNull(message = "payload is required")
    private Payload payload;

    public static class Payload{
        @Valid
        @NotNull(message = "code is required")
        private String code;

        @Valid
        @NotNull(message = "description is required")
        private String description;

        @Valid
        @NotNull(message = "gst is required")
        @DecimalMin(value = "0.10", inclusive = true)
        private BigDecimal gst;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getGst() {
            return gst;
        }

        public void setGst(BigDecimal gst) {
            this.gst = gst;
        }

        @Override
        public String toString() {
            return "Payload{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", gst='" + gst + '\'' +
                    '}';
        }
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AddHSNVO{" +
                "payload=" + payload +
                '}';
    }
}
