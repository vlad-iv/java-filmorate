package ru.yandex.practicum.filmorate.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@JdbcTest
@Import(JdbcUserRepository.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DisplayName("JdbcUserRepository")
class JdbcUserRepositoryTest {
	public static final long TEST_USER_ID = 1L;
	private final JdbcUserRepository userRepository;

	static User getTestUser() {
		User user = new User();
		user.setId(TEST_USER_ID);
		user.setEmail("email@email.com");
		user.setName("user");
		user.setBirthday(LocalDate.of(2000, 3,22));
		return user;
	}

	@Test
	@DisplayName("должен находить пользователя по ид")
	public void should_return_user_when_find_by_id() {

		Optional<User> userOptional = userRepository.getById(TEST_USER_ID);

		assertThat(userOptional)
				.isPresent()
				.get()
				.usingRecursiveComparison()
//				.ignoringExpectedNullFields()
				.isEqualTo(getTestUser());

	}
}