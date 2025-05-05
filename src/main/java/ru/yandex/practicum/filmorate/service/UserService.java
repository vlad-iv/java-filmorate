package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validation.UserValidation;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User createUser(User user) {
        UserValidation.validationForUser(user);
        return userStorage.createUser(user);
    }

    public User updateUser(User newUser) {
        UserValidation.validationForUser(newUser);
        if (!userStorage.findById(newUser.getId()).isPresent()) {
            throw new NotFoundException("Некорректный идентификатор пользователя");
        }
        return userStorage.update(newUser);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public Optional<User> findById(Long userId) {
        return userStorage.findById(userId);
    }

    public void addFriend(Long userId, Long friendId) {
        userStorage.addFriends(userId, friendId);
    }

    public void deleteFriend(Long userId, Long friendId) {
        userStorage.deleteFriends(userId, friendId);
    }

    public List<User> findAllFriends(Long userId) {
        return userStorage.findAllFriends(userId);
    }

    public List<User> mutualFriends(Long userId, Long friendId) {
        return userStorage.listMutualFriends(userId, friendId);
    }
}
