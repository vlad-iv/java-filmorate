package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.filmorate.Create;
import ru.yandex.practicum.filmorate.Update;
import ru.yandex.practicum.filmorate.validator.RealiseDateContraint;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@NotNull(groups = {Update.class})
	Long id;
	@Size(min=1 , max=10)
	@NotBlank(groups = {Update.class, Create.class})
	String name;
	String login;

	@Past(groups = {Create.class})
	LocalDate birthday;

	@NotNull(groups = {Update.class})
	@RealiseDateContraint
	LocalDate realiseDate;

}
