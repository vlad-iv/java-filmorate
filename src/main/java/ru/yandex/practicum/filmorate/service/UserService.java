package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

public interface UserService {
	User get(int userId);

	User save(User user);

	void update(User user);

	void addFriend(int userId, int friendId);

	void deleteFriend(int userId, int friendId);
}
