package ru.yandex.practicum.filmorate;

import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RestController
public class UserController {
	@Value("date.format")
	String dataFormat;

	DateTimeFormatter dateTimeFormatter;

	@Autowired
	UserService userService;

	public UserController() {
	}

	@PostConstruct
	public void init() {
		dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat);
	}

}
