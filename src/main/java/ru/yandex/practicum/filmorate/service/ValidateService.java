package ru.yandex.practicum.filmorate.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;

@Component
@Slf4j
public class ValidateService {
	public void validateUser(User user) {
		if (user.getName() == null || user.getName().isBlank()) {
			log.warn("user name is blank: {}", user);
			throw new RuntimeException("Ошибка в имени:....");
		}
		if (user.getBirthday().isAfter(LocalDate.now())) {
			throw new RuntimeException("invalid birthday");
		}

	}
}
