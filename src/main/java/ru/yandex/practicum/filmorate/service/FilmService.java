package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validation.FilmValidation;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmStorage filmStorage;
    private UserStorage userStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film addFilm(Film film) {
        FilmValidation.validationForFilm(film);
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film newFilm) {
        FilmValidation.validationForFilm(newFilm);
        if (!filmStorage.findById(newFilm.getId()).isPresent()) {
            throw new NotFoundException("Некорректный идентификатор фильма");
        }
        return filmStorage.update(newFilm);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Optional<Film> findById(Long filmId) {
        return filmStorage.findById(filmId);
    }

    public void addLike(Long filmId, Long userId) {
        filmStorage.addLike(filmId, userId);
    }

    public void deleteLike(Long filmId, Long userId) {
        filmStorage.deleteLike(filmId, userId);
    }

    public List<Film> topPopularMovies(Integer count) {
        return filmStorage.top10PopularMovies(count);
    }
}
