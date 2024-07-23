package ru.yandex.practicum.filmorate.controller;


import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.service.ValidateService;

/**
 * User controller.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
@RequestMapping("users")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {
	final ValidateService validateService;

	final UserService userService;

	@GetMapping("/{userId}")
	User get(@PathVariable Integer userId) {
		log.info("Get user by id={}", userId);
		return userService.get(userId);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	User save(@RequestBody @Valid User user) {
		log.info("Create User: {} - Started", user);
		validateService.validateUser(user);
		User saved = userService.save(user);
		log.info("Create User: {} - Finished", saved);
		return saved;
	}

	@PutMapping()
	void updateUser(@RequestBody @Valid User user) {// TODO id != null
		validateService.validateUser(user);
		userService.update(user);
	}

	@PutMapping("/users/{userId}/friends/{friendId}")
	public void addFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.addFriend(userId, friendId);
	}

	@DeleteMapping("/users/{userId}/friends/{friendId}")
	public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
		userService.deleteFriend(userId, friendId);
	}


}
