package ru.yandex.practicum.filmorate.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Repository
public class JdbcGenreRepository implements GenreRepository {
	@Override
	public User getById(final long id) {
		return null;
	}

	@Override
	public List<User> getAll() {
		return List.of();
	}

	@Override
	public void setFilmGenre(final List<Film> films) {

	}

	@Override
	public void loadFilmGenre(final List<Film> films) {

	}

	@Override
	public List<Genre> findByIds(final List<Long> ids) {
		return List.of();
	}

	@Override
	public List<Genre> getByIds(final List<Long> genreIds) {
		return List.of();
	}
}
