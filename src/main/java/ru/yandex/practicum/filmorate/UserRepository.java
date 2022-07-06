package ru.yandex.practicum.filmorate;

import java.util.List;

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
