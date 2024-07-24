package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;

/**
 * User logic.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public User get(int userId) {
//		final User user = userStorage.get(userId);
//		if (user == null) {
//			throw new NotFoundException("User with id=" + userId + " not found");
//		}
		final User user = userRepository.getById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void update(User user) {
		final Long userId = user.getId();
		userRepository.getById(userId) // TODO для JDBC можно перенести в метод update
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		userRepository.update(user);
	}

	@Override
	public void addFriend(int userId, int friendId) {
		final User user = userRepository.getById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		final User friend = userRepository.getById(friendId)
				.orElseThrow(() -> new NotFoundException("Friend not found with " + friendId));
		// TODO check userId and  friendId

		userRepository.addFriend(user.getId(), friend.getId());
	}

	@Override
	public void deleteFriend(int userId, int friendId) {
		final User user = userRepository.getById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		final User friend = userRepository.getById(friendId)
				.orElseThrow(() -> new NotFoundException("Friend not found with " + friendId));
		// TODO check userId and  friendId
		userRepository.deleteFriend(user.getId(), friend.getId());
	}
}
