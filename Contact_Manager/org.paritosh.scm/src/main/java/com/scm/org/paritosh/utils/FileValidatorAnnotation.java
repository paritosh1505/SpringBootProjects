package com.scm.org.paritosh.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface FileValidatorAnnotation {
    String message() default "Invalid File";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
