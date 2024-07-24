package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import ru.yandex.practicum.filmorate.model.User;

class UserTest {

	// Инициализация Validator
	private static final Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.usingContext().getValidator();
	}

	@Test
	void validateName() {
		User user = new User();
		user.setName(" ");

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertFalse(violations.isEmpty(), "Violation not found");
		ConstraintViolation<User> violation = violations.iterator().next();
		assertEquals(NotBlank.class, violation.getConstraintDescriptor().getAnnotation().annotationType(), "NotBlank violation not found");
		assertEquals("name", violation.getPropertyPath().toString(), "Not found violation under property name");
	}

	@Test
	void validateCorrectName() {
		User user = new User();
		user.setName("Name");

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertEquals(0, violations.size(), "Name is empty");

	}

}