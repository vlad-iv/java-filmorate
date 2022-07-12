package ru.yandex.practicum.filmorate.dao;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class UserStorage {
	int generator = 0;
	HashMap<Integer, User> userMap = new HashMap<>();

	public User get(int userId) {
		return userMap.get(userId);
	}

	public User save(User user) {
		user.setId(++generator);
		userMap.put(user.getId(), user);
		return user;
	}

	public void addFriend(User user, User friend) {
		user.getFriendIds().add(friend.getId());
		friend.getFriendIds().add(user.getId());
	}

	public void deleteFriend(User user, User friend) {
		user.getFriendIds().remove(friend.getId());
		friend.getFriendIds().remove(user.getId());
	}
}
