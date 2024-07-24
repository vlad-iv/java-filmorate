package ru.yandex.practicum.filmorate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import ru.yandex.practicum.filmorate.repository.GenreRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;

/**
 * User logic.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class BaseFilmService implements FilmService {
	final FilmRepository filmRepository;
	final UserRepository userRepository;
	final GenreRepository genreRepository;

	@Override
	public Film getById(long id) {
		final Film film = filmRepository.getById(id)
				.orElseThrow(() -> new NotFoundException("film with id=" + id + " not found"));
		return film;
	}

	@Override
	public Film update(Film film) {
		// Получить фильм по id (можно не делать, но проверить в репозитории что обновлена одна запись иначе исключение)
		final Film f = filmRepository.getById(film.getId()).orElseThrow();
		// Получить рейтинг по id
//		final Mpa mpa = mpaRepository.getById(film.getMpa().getId()).orElseThrow();
		// Получить жанры по списку id
		final List<Long> genreIds = film.getGenres().stream().map(Genre::getId).toList();

		final List<Genre> genres = genreRepository.getByIds(genreIds);
		if (genreIds.size() != genres.size()) {
			throw new NotFoundException("Жанры не найдены");
		}
		f.setName(film.getName());
//		f.setMpa(mpa);
		f.setGenres(new HashSet<>(genres));

		filmRepository.update(f);

		return null;
	}

	@Override
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
		return newfilm;
	}

//	List<Film> getAll() {
//		List<Film> films = userRepository.getAll(); // mpa
//		return films;
//	}


	@Override
	public void addLike(int userId, int filmId) {
		User user = userRepository.getById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		Film film = filmRepository.getById(filmId)
				.orElseThrow(() -> new NotFoundException("Film not found with " + userId));

		filmRepository.addLike(film.getId(), user.getId());
	}

	@Override
	public void deleteLike(int userId, int filmId) {
		User user = userRepository.getById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		Film film = filmRepository.getById(filmId)
				.orElseThrow(() -> new NotFoundException("Film not found with " + userId));
		filmRepository.deleteLike(film.getId(), user.getId());
	}
}
