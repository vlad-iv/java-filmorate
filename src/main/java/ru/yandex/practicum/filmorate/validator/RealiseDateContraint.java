package ru.yandex.practicum.filmorate.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Documented
@Constraint(validatedBy = RealiseDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RealiseDateContraint {
	String message() default "Film realise must be after 1895";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}