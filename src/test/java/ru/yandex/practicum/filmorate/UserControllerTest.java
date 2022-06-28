package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */

class UserControllerTest {
	@Test
	void validateBirthday() {
		final UserController userController = new UserController();
		final User user = new User();
		user.setName("name");
		user.setBirthday(LocalDate.MAX);

		assertThrows(RuntimeException.class, () -> userController.validate(user) );
	}

}