package ru.yandex.practicum.filmorate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.yandex.practicum.filmorate.dao.DBUserRepository;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@JdbcTest
@Import(DBUserRepository.class)
class DBUserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindUserById() {

		User user = userRepository.getById(1);
		assertNotNull(user);

		assertEquals(1, user.getId());
		assertEquals("email@email.com", user.getEmailNew());

	}
}