package ru.yandex.practicum.filmorate.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class UserStorage {
	long generator = 0;
	HashMap<Long, User> userMap = new HashMap<>();
	HashMap<Long, Set<Long>> userFriendIds = new HashMap<>();

	public Optional<User> get(long userId) {
		return Optional.of(userMap.get(userId));
	}

	public User save(User user) {
		user.setId(++generator);
		userMap.put(user.getId(), user);
		return user;
	}

	public void update(User user) {
		userMap.put(user.getId(), user);
	}

	public void addFriend(User user, User friend) {

		Set<Long> uFriendIds = userFriendIds.computeIfAbsent(user.getId(), id -> new HashSet<>());
		uFriendIds.add(friend.getId());

		Set<Long> fFriendIds = userFriendIds.computeIfAbsent(friend.getId(), id -> new HashSet<>());
		fFriendIds.add(user.getId());
	}

	public void deleteFriend(User user, User friend) {
		Set<Long> uFriendIds = userFriendIds.computeIfAbsent(user.getId(), id -> new HashSet<>());
		uFriendIds.remove(friend.getId());

		Set<Long> fFriendIds = userFriendIds.computeIfAbsent(friend.getId(), id -> new HashSet<>());
		fFriendIds.remove(user.getId());
	}
}
