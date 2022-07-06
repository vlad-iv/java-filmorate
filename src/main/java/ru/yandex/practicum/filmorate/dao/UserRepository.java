package ru.yandex.practicum.filmorate.dao;

import java.util.List;

import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface UserRepository {
	User getById(long id);

	List<User> getAll();

	User save(User data);

	void update(User data);

	void addFriend(long userId, long friend);
}
