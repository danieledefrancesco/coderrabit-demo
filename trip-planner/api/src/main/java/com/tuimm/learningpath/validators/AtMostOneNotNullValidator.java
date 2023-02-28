package com.tuimm.learningpath.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.Objects;

public class AtMostOneNotNullValidator implements ConstraintValidator<AtMostOneNotNull, Object> {

    private String[] fields;
    @Override
    public void initialize(AtMostOneNotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);
        return Arrays.stream(fields).map(beanWrapper::getPropertyValue).filter(Objects::nonNull).count() <= 1;
    }
}
