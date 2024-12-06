package org.training.capital.microservice.msorder.input.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,
         ElementType.METHOD,
         ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckNotValidWordsImpl.class,
                           CheckNotValidWordsObjectImpl.class
})
public @interface CheckNotValidWords {
    String[] value();

    String message() default "{value} not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
