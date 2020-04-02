package com.university.accounts.stereotypes;

import com.university.accounts.entity.AccCode;
import com.university.accounts.utils.AllowedCurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = AllowedCurrencyValidator.class)
public @interface AllowedCurrency {

    AccCode[] allowedValues() default {AccCode.RUB, AccCode.EUR, AccCode.USD};
    String message() default "must be any of {allowedValues}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}