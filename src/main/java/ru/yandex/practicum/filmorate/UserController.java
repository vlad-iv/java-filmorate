package ru.yandex.practicum.filmorate;

import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
@Slf4j
public class UserController {
	@Autowired
	UserService service;

	public UserController() {
	}

	@PostMapping("/users")
	public User create(@Valid @RequestBody final User user) {
		log.info("Creating user {}", user);
		return service.save(user);
	}

	@GetMapping("/users/{id}")
	public User get(@PathVariable long id) {
		log.info("Get user id={}", id);
		return service.get(id);
	}

}
