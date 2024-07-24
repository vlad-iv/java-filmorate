package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

/**
 * Film service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface FilmService {

	Film getById(long id);

	Film update(Film film);

	Film save(Film film);

	void addLike(int userId, int filmId);

	void deleteLike(int userId, int filmId);
}
