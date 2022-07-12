package ru.yandex.practicum.filmorate.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
@RequestMapping("users")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/{userId}")
	User getUser(@PathVariable int userId) {
		log.info("Get user by id={}", userId);
		return userService.get(userId);
	}

	@PostMapping()
	User saveUser(@RequestBody @Valid User user) {
		validate(user);
		User saved = userService.save(user);
		return saved;
	}

	@PutMapping("/users/{userId}/friends/{friendId}")
	public void addFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.addFriend(userId, friendId);
	}

	@DeleteMapping("/users/{userId}/friends/{friendId}")
	public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.deleteFriend(userId, friendId);
	}

	void validate(User user) {
		if (user.getBirthday().isAfter(LocalDate.now())) {
			throw new RuntimeException("invalid birthday");
		}
	}

}
