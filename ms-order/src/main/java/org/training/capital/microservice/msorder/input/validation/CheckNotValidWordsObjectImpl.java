package org.training.capital.microservice.msorder.input.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckNotValidWordsObjectImpl implements ConstraintValidator<CheckNotValidWords, Object> {

    private CheckNotValidWords anno;

    @Override
    public void initialize(final CheckNotValidWords constraintAnnotation) {
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(final Object value,
                           final ConstraintValidatorContext context) {
        Class<?>     classLoc          = value.getClass();
        Field[]      declaredFieldsLoc = classLoc.getDeclaredFields();
        List<String> errors            = new ArrayList<>();
        for (Field declaredFieldLoc : declaredFieldsLoc) {
            Class<?> typeLoc = declaredFieldLoc.getType();
            if (typeLoc.getName()
                       .equals(String.class.getName())) {
                declaredFieldLoc.setAccessible(true);
                try {
                    String oLoc = (String) declaredFieldLoc.get(value);
                    Arrays.stream(anno.value())
                          .forEach(s -> {
                              if (oLoc.contains(s)) {
                                  errors.add("Field : " + declaredFieldLoc.getName() + " can not contain " + s);
                              }
                          });
                } catch (Exception eParam) {
                }


            }
        }
        if (!errors.isEmpty()){
            context.disableDefaultConstraintViolation();
            String collectLoc = errors.stream()
                                      .collect(Collectors.joining(" --- "));
            context.buildConstraintViolationWithTemplate(collectLoc).addConstraintViolation();
            return false;
        }
        return true;
    }
}
