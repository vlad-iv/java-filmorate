package ru.yandex.practicum.filmorate.repository;

import ru.yandex.practicum.filmorate.model.User;

/**
 * User Repository.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface FriendRepository {
	void add(long userId, long friendId);

	void delete(long userId, long friendId);

// OR

	void add(User user, User friend);

	void delete(User user, User friend);
}
