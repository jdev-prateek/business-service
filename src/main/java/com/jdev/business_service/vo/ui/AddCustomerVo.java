package com.jdev.business_service.vo.ui;

import com.jdev.business_service.constants.CustomerTypeConstants;
import com.jdev.business_service.utils.validator.EnumValidatorConstraint;
import com.jdev.business_service.utils.validator.UUIDValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AddCustomerVo {
    @Valid
    @NotNull(message = "payload must not be null")
    private Payload payload;

    public static class Payload{
        @Valid
        @NotNull(message = "firstName is required")
        private String firstName;

        private String middleName;

        @Valid
        @NotNull(message = "lastName is required")
        private String lastName;

        @Email
        @NotNull(message = "email is required")
        private String email;

        @NotNull(message = "phone is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "phone number invalid")
        private String phone;

        @NotNull(message = "type is required")
        @EnumValidatorConstraint(enumClass = CustomerTypeConstants.class, message = "type must be one of BUSINESS or RETAIL")
        private String type;

        @NotNull(message = "businessId is required")
        @UUIDValidator(message = "uuid invalid")
        private String businessId;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type.toUpperCase();
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
