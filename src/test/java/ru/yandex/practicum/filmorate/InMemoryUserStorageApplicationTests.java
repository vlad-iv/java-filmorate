package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class InMemoryUserStorageApplicationTests {
    private InMemoryUserStorage userStorage;

    @BeforeEach
    public void setUp() {
        userStorage = new InMemoryUserStorage();
    }

    private User createUser(String name, String login, String email, LocalDate birthday) {
        return User.builder()
                .name(name)
                .login(login)
                .email(email)
                .birthday(birthday)
                .build();
    }

    /**
     * Создание пользователя.
     */
    @Test
    void createUser() {
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                LocalDate.now());
        User create = userStorage.createUser(user);

        Assertions.assertEquals(user.getName(), create.getName());
    }

    /**
     * Обновление пользователя.
     */
    @Test
    void updateUser() {
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                LocalDate.now());
        User createUser = userStorage.createUser(user);
        createUser.setName("Обновлённый пользователь");
        User update = userStorage.update(createUser);
        Assertions.assertEquals("Обновлённый пользователь", update.getName());
    }

    /**
     * Список всех пользователей.
     */
    @Test
    void shouldGetAllUsers() {
        User user = createUser("Тестовый пользователь №1", "smolcap", "user1@example.com",
                LocalDate.now());
        User user2 = createUser("Тестовый пользователь №2", "smolcap", "user1@example.com",
                LocalDate.now());
        userStorage.createUser(user);
        userStorage.createUser(user2);

        List<User> getUsersList = userStorage.getAllUsers();

        Assertions.assertEquals(2, getUsersList.size());
    }

    /**
     * Поиск пользователя по ID.
     */
    @Test
    void shouldGetUserById() {
        User user = createUser("Тестовый пользователь №1", "smolcap", "user1@example.com",
                LocalDate.now());
        userStorage.createUser(user);

        Optional<User> findById = userStorage.findById(user.getId());

        Assertions.assertEquals(user.getId(), findById.get().getId());
    }

    /**
     * Электронная почта не может быть пустой и должна содержать символ @.
     */
    @Test
    void shouldntEmailToBeEmptyAndNoHaveChar() {
        User user = createUser("Тестовый пользователь №1", "smolcap", "user1example.com",
                LocalDate.now());

        Assertions.assertThrows(ValidationException.class, () -> {
            userStorage.createUser(user);
        });
    }

    /**
     * Логин не может быть пустым и содержать пробелы.
     */
    @Test
    void shouldntLoginToBeEmptyAndHaveSpaces() {
        User user = createUser("Тестовый пользователь", "", "user1@example.com",
                LocalDate.now());

        Assertions.assertThrows(ValidationException.class, () -> {
            userStorage.createUser(user);
        });
    }

    /**
     * Имя для отображения может быть пустым — в таком случае будет использован логин.
     */
    @Test
    void shouldNameToBeEmptyButUseLogin() {
        User user = createUser("", "smolcap", "user1@example.com",
                LocalDate.now());

        User create = userStorage.createUser(user);

        Assertions.assertEquals("smolcap", create.getName());
    }

    /**
     * Дата рождения не может быть в будущем.
     */
    @Test
    void shouldntBirthdayToBeInAfter() {
        LocalDate invalidBirthday = LocalDate.of(2025, 11, 12);
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                invalidBirthday);

        Assertions.assertThrows(ValidationException.class, () -> {
            userStorage.createUser(user);
        });
    }

    /**
     * Добавление в друзья и удаление из друзей пользователя.
     */
    @Test
    void shouldAddAndDeleteFriend() {
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                LocalDate.now());
        User createUser = userStorage.createUser(user);
        User friend = createUser("Тестовый пользователь друг", "smolcap", "user1@example.com",
                LocalDate.now());
        User createFriend = userStorage.createUser(friend);

        userStorage.addFriends(createUser.getId(), createFriend.getId());

        Assertions.assertTrue(createUser.getFriends().contains(createFriend.getId()),
                "Пользователь должен быть другом");

        userStorage.deleteFriends(createUser.getId(), createFriend.getId());
        Assertions.assertFalse(createUser.getFriends().contains(createFriend.getId()),
                "Пользователь не должен находиться в друзьях");
    }

    /**
     * Вывод списка друзей пользователя.
     */
    @Test
    void shouldFindAllFriendsUser() {
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                LocalDate.now());
        User createUser = userStorage.createUser(user);
        User friend = createUser("Тестовый пользователь друг", "smolcap", "user1@example.com",
                LocalDate.now());
        User createFriend = userStorage.createUser(friend);

        userStorage.addFriends(createUser.getId(), createFriend.getId());

        List<User> findAllFriend = userStorage.findAllFriends(createUser.getId());

        Assertions.assertTrue(createUser.getFriends().contains(createFriend.getId()),
                "Пользователь должен быть другом");
        Assertions.assertTrue(findAllFriend.stream().anyMatch(f -> f.getId().equals(createFriend.getId())),
                "Должен находиться пользователь с ID " + createFriend.getId());
    }

    /**
     * Список друзей, общих с другим пользователем.
     */
    @Test
    void shouldGetListFriendsMutualAnyUser() {
        User user = createUser("Тестовый пользователь", "smolcap", "user1@example.com",
                LocalDate.now());
        User createUser = userStorage.createUser(user);

        User user2 = createUser("Тестовый пользователь2", "smolcap2", "user2@example.com",
                LocalDate.now());
        User createUser2 = userStorage.createUser(user2);

        User user3 = createUser("Тестовый пользователь3", "smolcap", "user3@example.com",
                LocalDate.now());
        User createUser3 = userStorage.createUser(user3);

        userStorage.addFriends(createUser.getId(), createUser2.getId());
        userStorage.addFriends(createUser.getId(), createUser3.getId());
        userStorage.addFriends(createUser2.getId(), createUser3.getId());

        List<User> mutualFriend = userStorage.listMutualFriends(createUser.getId(), createUser2.getId());

        Assertions.assertTrue(mutualFriend.stream().anyMatch(m -> m.getId().equals(createUser3.getId())));
    }
}
