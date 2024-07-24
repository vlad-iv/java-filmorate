package ru.yandex.practicum.filmorate.repository;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.model.Film;

/**
 * Film Repository.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface FilmRepository {
	Optional<Film> getById(long id);

	Film save(Film data);

	void update(Film data);

	List<Film> getAll();

	void addLike(long filmId, long userId);

	void deleteLike(long filmId, long userId);

	List<Film> getTopPopular(int count);
}
