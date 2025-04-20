package com.jdev.business_service.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator  implements ConstraintValidator<EnumValidatorConstraint, String> {
    private Set<String> allowedValues;

    @Override
    public void initialize(EnumValidatorConstraint constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null ||  allowedValues.contains(value.toUpperCase());
    }
}
