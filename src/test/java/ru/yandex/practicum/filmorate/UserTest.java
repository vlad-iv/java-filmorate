package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
		assertEquals(1, violations.size(), "Name is empty");
	}
	@Test
	void validateCorrectName() {
		User user = new User();
		user.setName("Name");


		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertEquals(0, violations.size(), "Name is empty");

	}

}