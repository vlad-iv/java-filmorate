package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

public interface FilmRepository {
	Film get(int filmId);

	Film save(Film film);

	void addLike(Film film, User user);

	void deleteLike(Film film, User user);
}
