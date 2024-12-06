package org.training.capital.microservice.msorder.input.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckNotValidWordsImpl implements ConstraintValidator<CheckNotValidWords,String> {

    private CheckNotValidWords anno;

    @Override
    public void initialize(final CheckNotValidWords constraintAnnotation) {
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return Arrays.stream(anno.value()).noneMatch(s -> {
            boolean containsLoc = value.contains(s);
            return containsLoc;
        });
    }
}
