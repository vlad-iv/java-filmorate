package ru.yandex.practicum.filmorate;

import java.util.List;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface GenreRepository {
	User getById(long id);

	List<User> getAll();

	void setFilmGenre(Film film);
	void loadFilmGenre(Film film);
}
