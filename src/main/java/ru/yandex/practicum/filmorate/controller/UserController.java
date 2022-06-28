package ru.yandex.practicum.filmorate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.service.ValidateService;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
@RequestMapping("users")
@Validated
@Slf4j
public class UserController {
	final ValidateService validateService;
	UserRepository repository;

	public UserController(ValidateService validateService) {
		this.validateService = validateService;
	}

	@GetMapping()
	User getUser() {
//		repository.getById()
//		User user = repository.findById()
//		List<User> users =  repository.findByName()
//		List<User> users =  repository.findAll()
//
//		repository.search(UserFilter)
//		repository.find(UserFilter)
		return new User(1L, "User", null);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	User saveUser(@RequestBody @Valid User user) { // TODO id ignored, generated
		log.info("Create User: {} - Started", user);
		validateService.validateUser(user);
		repository.save(user);
		log.info("Create User: {} - Finished", user);
		return user;
	}

	@PutMapping()
	void updateUser(@RequestBody @Valid User user) {// TODO id != null
		validateService.validateUser(user);
		repository.save(user);
	}

	private void save(User user) {

	}


	@GetMapping("films")
	User getFilms() {
		return new User(2L, "Film", null);
	}
}
