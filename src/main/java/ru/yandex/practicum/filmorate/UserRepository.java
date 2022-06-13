package ru.yandex.practicum.filmorate;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface UserRepository extends Repository<User> {
	void addFriend(long userId, long friend);
}
