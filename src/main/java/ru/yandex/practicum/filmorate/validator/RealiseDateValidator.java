package ru.yandex.practicum.filmorate.validator;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RealiseDateValidator implements ConstraintValidator<RealiseDateContraint, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.isAfter(LocalDate.of(1895, 1, 1));
	}
}
