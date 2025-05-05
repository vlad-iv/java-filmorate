package ru.yandex.practicum.filmorate.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.UserValidation;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryUserStorage implements UserStorage {
    private static Logger log = LoggerFactory.getLogger(InMemoryUserStorage.class);
    private Map<Long, User> users = new HashMap<>();

    @Override
    public User createUser(User user) {
        user.setId(generationId());
        UserValidation.validationForUser(user);
        users.put(user.getId(), user);
        log.info("Пользователь создан {} и добавлен в хранилище под ID: {}", user, user.getId());
        return user;
    }

    @Override
    public User update(User newUser) {
        UserValidation.validationForUser(newUser);
        users.put(newUser.getId(), newUser);
        log.info("Пользователь обновлён {} и добавлен в хранилище под ID: {}", newUser, newUser.getId());
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long userId) {
        User user = users.get(userId);
        log.info("Получаем пользователя по идентификатору с ID {}", userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void addFriends(Long userId, Long friendId) {
        log.info("Добавляем к пользователю с ID {} друга с ID {}", userId, friendId);
        User user = findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + userId + " не найден."));
        User friend = findById(friendId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + friendId + " не найден."));

        if (user.getFriends().contains(friendId)) {
            log.error("Пользователь с ID {} уже является другом", friend);
            throw new ValidationException("Пользователь уже является другом");
        }

        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
        update(user);
        update(friend);

        log.info("Пользователь {} успешно добавил в друзья пользователя {}", userId, friendId);
        log.info("Список друзей пользователя {}: {}", user.getName(), user.getFriends());
        log.info("Список друзей пользователя {}: {}", friend.getName(), friend.getFriends());
    }

    @Override
    public void deleteFriends(Long userId, Long friendId) {
        log.info("Удаляем у пользователя с ID {} друга с ID {}", userId, friendId);
        User user = findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + userId + " не найден."));
        User friend = findById(friendId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + friendId + " не найден."));

        if (!user.getFriends().contains(friendId)) {
            log.error("Пользователь с ID {} не является другом", friend);
            throw new ValidationException("Пользователь не является другом");
        }

        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
        update(user);
        update(friend);

        log.info("Пользователь {} успешно удалил пользователя {} из своих друзей", userId, friendId);
        log.info("Список друзей пользователя {}: {}", user.getName(), user.getFriends());
        log.info("Список друзей пользователя {}: {}", friend.getName(), friend.getFriends());
    }

    @Override
    public List<User> findAllFriends(Long userId) {
        log.info("Пользователь {} запрашивает список друзей", userId);
        User user = findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + userId + "не найден."));

        log.info("Список друзей пользователя {}: {}", userId, user.getFriends());
        Set<Long> friendsId = user.getFriends();

        List<User> friendList = friendsId.stream()
                .map(friendId -> findById(friendId)
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return friendList;
    }

    @Override
    public List<User> listMutualFriends(Long userId, Long mutualId) {
        log.info("Попытка найти общих друзей между пользователями с ID: {} и {}", userId, mutualId);
        User user = findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + userId + " не найден."));
        User friend = findById(mutualId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + mutualId + " не найден."));

        Set<Long> friendsOfUser = user.getFriends();
        friendsOfUser.retainAll(friend.getFriends());

        List<User> mutualFriends = friendsOfUser.stream()
                .map(mutualIds -> findById(mutualIds)
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("Общие друзья между пользователями {} и {}: {}", userId, mutualId, friendsOfUser);

        return mutualFriends;
    }


    private long generationId() {
        long currentMaxId = users.keySet().stream().mapToLong(id -> id).max().orElse(0);
        return ++currentMaxId;
    }
}
