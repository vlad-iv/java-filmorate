package ru.yandex.practicum.filmorate.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.model.User;

class ValidateServiceTest {

	@Test
	void name() {
		ValidateService validateService = new ValidateService();
		User user = new User();
		user.setName("");
		assertThrows(RuntimeException.class, () -> validateService.validateUser(user) );
	}
}