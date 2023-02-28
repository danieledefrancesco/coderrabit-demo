package com.tuimm.learningpath.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotNull {
    String[] fields() default {};
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "At least one field must be not null.";
}
