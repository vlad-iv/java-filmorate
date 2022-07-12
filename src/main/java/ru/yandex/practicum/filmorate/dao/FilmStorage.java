package ru.yandex.practicum.filmorate.dao;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class FilmStorage {
	int generator = 0;
	HashMap<Integer, Film> filmMap = new HashMap<>();

	public Film get(int filmId) {
		return filmMap.get(filmId);
	}

	public Film save(Film film) {
		film.setId(++generator);
		filmMap.put(film.getId(), film);
		return film;
	}

	public void addLike(Film film, User user) {
		// TODO
	}

	public void deleteLike(Film film, User user) {
		// TODO
	}
}
