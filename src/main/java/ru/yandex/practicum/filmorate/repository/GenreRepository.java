package ru.yandex.practicum.filmorate.repository;

import java.util.List;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface GenreRepository {
	User getById(long id);

	List<User> getAll();

	void setFilmGenre(List<Film> films);

	void loadFilmGenre(List<Film> films);

	List<Genre> findByIds(List<Long> ids);

	List<Genre> getByIds(List<Long> genreIds);
}
