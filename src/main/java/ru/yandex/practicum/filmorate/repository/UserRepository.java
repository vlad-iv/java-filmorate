package ru.yandex.practicum.filmorate.repository;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.model.User;

/**
 * User Repository.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface UserRepository {
	Optional<User> getById(long id);

	List<User> getAll();

	User save(User user); // insert, create

	void addFriend(long userId, long friend);

	void deleteFriend(long userId, long friend);

	void update(User user);
}
