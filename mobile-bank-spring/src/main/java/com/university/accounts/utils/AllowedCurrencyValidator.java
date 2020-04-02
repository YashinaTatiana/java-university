package com.university.accounts.utils;


import com.university.accounts.stereotypes.AllowedCurrency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllowedCurrencyValidator implements ConstraintValidator<AllowedCurrency, Enum<?>> {

    private Set<Enum<?>> allowedValues;

    @Override
    public void initialize(AllowedCurrency constraintAnnotation) {
        allowedValues = Stream.of(constraintAnnotation.allowedValues()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        return allowedValues.contains(value);
    }
}
