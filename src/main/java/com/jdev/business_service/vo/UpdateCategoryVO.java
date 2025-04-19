package com.jdev.business_service.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class UpdateCategoryVO {
    @Valid
    @NotNull(message = "payload must not be null")
    private Payload payload;

    public static class Payload{
        @Valid
        @NotNull(message = "oldName must not be  null")
        private String oldName;

        @Valid
        @NotNull(message = "newName must not be  null")
        private String newName;

        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
