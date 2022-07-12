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
public class InMemoryFilmRepository implements FilmRepository {
	int generator = 0;
	HashMap<Integer, Film> filmMap = new HashMap<>();

	@Override
	public Film get(int filmId) {
		return filmMap.get(filmId);
	}

	@Override
	public Film save(Film film) {
		film.setId(++generator);
		filmMap.put(film.getId(), film);
		return film;
	}

	@Override
	public void addLike(Film film, User user) {
		// TODO
	}

	@Override
	public void deleteLike(Film film, User user) {
		// TODO
	}
}
