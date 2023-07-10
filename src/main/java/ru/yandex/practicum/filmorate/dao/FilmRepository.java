package ru.yandex.practicum.filmorate.dao;

import java.util.List;

import ru.yandex.practicum.filmorate.model.Film;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface FilmRepository {
	Film getById(long id);

	List<Film> getAll();

	Film save(Film data);

	void update(Film data);

}
