package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.filmorate.validator.RealiseDateContraint;

/**
 * Film.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Film {
	long id;
	String name;
	@NotNull
	Mpa mpa; // films.mpa_id <- mpa.get()
	Set<Genre> genres; // genre_films.film_id и genre_id
	@NotNull
	@RealiseDateContraint
	LocalDate realiseDate;
}
