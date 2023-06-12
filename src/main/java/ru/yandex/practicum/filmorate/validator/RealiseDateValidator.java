package ru.yandex.practicum.filmorate.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RealiseDateValidator implements ConstraintValidator<RealiseDateContraint, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.isAfter(LocalDate.of(1895, 1, 1));
	}
}
