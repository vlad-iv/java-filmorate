package ru.yandex.practicum.filmorate;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	private String email;
	@NotBlank
	private String login;

	private String name;

	private LocalDate birthday;
}
