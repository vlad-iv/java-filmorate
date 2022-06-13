package ru.yandex.practicum.filmorate;

import org.springframework.stereotype.Component;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class InMemoryUserRepository extends AbstractRepository<User> implements UserRepository {
	@Override
	public void addFriend(long userId, long friend) {
		// TODO
	}
}
