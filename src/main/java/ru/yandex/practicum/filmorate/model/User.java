package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	Long id;

	@Email
	String email;

	@NotBlank
	String login;

	@Size(min = 1, max = 10)
	String name;

	@Past
	LocalDate birthday;

}
