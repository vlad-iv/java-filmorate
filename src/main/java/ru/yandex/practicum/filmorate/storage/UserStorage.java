package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User createUser(User user);

    User update(User newUser);

    List<User> getAllUsers();

    Optional<User> findById(Long userId);

    void addFriends(Long userId, Long friendId);

    void deleteFriends(Long userId, Long friendId);

    List<User> findAllFriends(Long userId);

    List<User> listMutualFriends(Long userId, Long mutualId);

}
