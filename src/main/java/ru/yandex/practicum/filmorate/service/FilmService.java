package ru.yandex.practicum.filmorate.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.dao.FilmRepository;
import ru.yandex.practicum.filmorate.dao.GenreRepository;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

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
		// Валидируем данные относительно БД
		if (film.getGenres() != null) {
//			for (Genre genre : film.getGenres()) {
//				Genre g = genreRepository.findById(genre.getId());
//				if (g == null) {
//					throw new NotFoundException("genre with id=" + genre.getId() + " not found");
//				}
//			}
			List<Long> ids = film.getGenres().stream()
					.map(f -> film.getId())
					.collect(Collectors.toList());
			List<Genre> genres = genreRepository.findByIds(ids); // where GENRE_ID in ( :ids )
			if (genres.size() != ids.size()) {
				throw new NotFoundException("genre with ids=" + ids + " not found");
			}
			film.setGenres(new HashSet<>(genres));
		}

		final Film newfilm = filmRepository.save(film);
		genreRepository.setFilmGenre(Collections.singletonList(newfilm));
		return newfilm;
	}

	public Film get(long id) {
		final Film film = filmRepository.getById(id);
		if (film == null) {
			throw new NotFoundException("film with id=" + id + " not found");
		}
//		Optional<Film> optionalFilm;
//		Film f = optionalFilm
//				.orElseThrow(() -> new NotFoundException("film with id=" + id + " not found"));

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
