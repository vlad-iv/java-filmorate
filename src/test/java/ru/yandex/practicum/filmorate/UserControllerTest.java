package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidateService;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */

class UserControllerTest {

	//	ObjectMapper objectMapper; // Замена Gson (это Jackson, по умолчанию в спринге)
	@Test
	void validateBirthday() {
		final ValidateService validateService = new ValidateService();
		final User user = new User();
		user.setName("name");
		user.setBirthday(LocalDate.MAX);

		assertThrows(RuntimeException.class, () -> validateService.validateUser(user));
	}

}