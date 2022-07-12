package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.dao.FilmRepository;
import ru.yandex.practicum.filmorate.dao.UserStorage;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

/**
 * User logic.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class BaseFilmService implements FilmService {
	final UserStorage userStorage;
	final FilmRepository filmRepository;

	@Override
	public void addLike(int userId, int filmId) {
		User user = userStorage.get(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		Film film = filmRepository.get(filmId);
		// TODO check userId and  friendId

		filmRepository.addLike(film, user);
	}

	@Override
	public void deleteLike(int userId, int filmId) {
		User user = userStorage.get(userId)
				.orElseThrow(() -> new NotFoundException("User not found with " + userId));
		Film film = filmRepository.get(filmId);
		// TODO check userId and  friendId
		filmRepository.deleteLike(film, user);
	}
}
