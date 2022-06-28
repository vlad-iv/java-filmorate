package ru.yandex.practicum.filmorate.dao;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserRepository {
	private long generatorId = 0;

	public long generateId() {
		return ++generatorId;
	}

	public void save(User user) {
		user.setId(generateId());
		if (user.getName().isBlank()) {
			///
			user.setName(user.getName());
		}
	}
}
