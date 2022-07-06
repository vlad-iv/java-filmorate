package ru.yandex.practicum.filmorate;

import java.util.Collections;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.dao.GenreRepository;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
//@Service
@RequiredArgsConstructor
public class FilmService {

	final FilmRepository filmRepository;
	final GenreRepository genreRepository;
	final UserRepository userRepository;

	public Film save(Film film) {
		final Film newfilm = filmRepository.save(film);
		genreRepository.setFilmGenre(Collections.singletonList(newfilm));
		return newfilm;
	}

	public Film get(long id) {
		final Film film = filmRepository.getById(id);
		if (film == null) {
			throw new NotFoundException("film with id=" + id + " not found");
		}
		genreRepository.loadFilmGenre(Collections.singletonList(film));
		return film;
	}


//	List<Film> getAll() {
//		List<Film> films = filmStrorage.getAll(); // mpa
//		for (Film film : films) {
//			genreStrorage.loadFilmGenre(film);
//		}
//		return films;
//	}

	/*

	 */
}
