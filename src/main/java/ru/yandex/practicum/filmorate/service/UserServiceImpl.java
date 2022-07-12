package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.dao.UserStorage;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

/**
 * User logic.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserStorage userStorage;

	@Override
	public User get(int userId) {
//		final User user = userStorage.get(userId);
//		if (user == null) {
//			throw new NotFoundException("User with id=" + userId + " not found");
//		}
		final User user = userStorage.get(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		return user;
	}

	@Override
	public User save(User user) {
		return userStorage.save(user);
	}

	@Override
	public void update(User user) {
		if (userStorage.get(user.getId()) == null) {
			throw new RuntimeException("User not found");
		}
		userStorage.update(user);
	}

	@Override
	public void addFriend(int userId, int friendId) {
		final User user = userStorage.get(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		final User friend = userStorage.get(friendId)
				.orElseThrow(() -> new NotFoundException("Friend not found with " + friendId));
		// TODO check userId and  friendId

		userStorage.addFriend(user, friend);
	}

	@Override
	public void deleteFriend(int userId, int friendId) {
		final User user = userStorage.get(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		final User friend = userStorage.get(friendId)
				.orElseThrow(() -> new NotFoundException("Friend not found with " + friendId));
		// TODO check userId and  friendId
		userStorage.deleteFriend(user, friend);
	}
}
