package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;

import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.model.User;

class UserTest {

	// Инициализация Validator
	private static Validator validator;
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