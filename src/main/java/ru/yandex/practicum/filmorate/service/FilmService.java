package ru.yandex.practicum.filmorate.service;

public interface FilmService {
	void addLike(int userId, int filmId);

	void deleteLike(int userId, int filmId);
}
