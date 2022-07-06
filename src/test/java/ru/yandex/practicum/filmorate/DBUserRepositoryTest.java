package ru.yandex.practicum.filmorate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class DBUserRepositoryTest {
	private final UserRepository userStorage;

	@Test
	public void testFindUserById() {

		User user = userStorage.getById(1);
		assertNotNull(user);
//		assertThat(userOptional)
//				.isPresent()

		assertEquals(1, user.getId());
		assertEquals("@mail.ru", user.getEmailNew());

		assertThat(user)
				.hasFieldOrPropertyWithValue("id", 1)
				.hasFieldOrPropertyWithValue("email", "@mail.ru");


		Optional<User> userOptional = Optional.of(user);

		assertThat(userOptional)
				.isPresent()
				.hasValueSatisfying(u ->
						assertThat(u).hasFieldOrPropertyWithValue("id", 1)
				);
	}
}