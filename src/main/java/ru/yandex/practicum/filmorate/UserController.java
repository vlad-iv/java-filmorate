package ru.yandex.practicum.filmorate;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
@RequestMapping("users")
public class UserController {
	@GetMapping()
	User getUser() {
		return new User(1, "User", null);
	}

	@PostMapping()
	User saveUser(@RequestBody User user) { // TODO id ignored, generated
		validate(user);
		save(user);
		return user;
	}

	@PutMapping()
	void updateUser(@RequestBody User user) {// TODO id != null
		validate(user);
		save(user);
	}

	private void save(User user) {

	}

	void validate(User user) {
		if (user.getBirthday().isAfter(LocalDate.now())) {
			throw new  RuntimeException("invalid birthday");
		}
	}

	@GetMapping("films")
	User getFilms() {
		return new User(2, "Film", null);
	}
}
