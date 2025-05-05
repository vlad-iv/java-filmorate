package ru.yandex.practicum.filmorate.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.FilmValidation;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private static Logger log = LoggerFactory.getLogger(InMemoryFilmStorage.class);
    private Map<Long, Film> films = new HashMap<>();

    @Override
    public Film addFilm(Film film) {
        film.setId(generationId());
        FilmValidation.validationForFilm(film);
        films.put(film.getId(), film);
        log.info("Фильм создан {} и добавлен в хранилище {}", film, film.getId());
        return film;
    }

    @Override
    public Film update(Film newFilm) {
        FilmValidation.validationForFilm(newFilm);
        films.put(newFilm.getId(), newFilm);
        log.info("Фильм {} обновлен с Id {}", newFilm, newFilm.getId());
        return newFilm;
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Optional<Film> findById(Long filmId) {
        Film film = films.get(filmId);
        log.info("Получаем фильм по идентификатору с ID {}", filmId);
        return Optional.ofNullable(film);
    }

    @Override
    public void addLike(Long filmId, Long userId) {
        log.info("Добавляем лайк к фильму с ID {} от пользователя с ID {}", filmId, userId);
        Film film = findById(filmId)
                .orElseThrow(() -> {
                    log.error("Фильм с ID {} не найден", filmId);
                    return new ValidationException("Фильм не найден");
                });
        if (film.getUserLikes().contains(userId)) {
            log.warn("Пользователь {} уже поставил лайк фильму {}", userId, filmId);
            throw new ValidationException("Пользователь может поставить только один лайк фильму");
        }
        film.getUserLikes().add(userId);
        film.setLikes(film.getLikes() + 1);
        log.info("Лайк от пользователя {} успешно добавлен к фильму {}", userId, filmId);
    }

    @Override
    public void deleteLike(Long filmId, Long userId) {
        log.info("Удаляем лайк у фильма с ID {} от пользователя с ID {}", filmId, userId);
        Film film = findById(filmId)
                .orElseThrow(() -> {
                    log.error("Фильм с ID {} не найден", filmId);
                    return new ValidationException("Фильм не найден");
                });

        if (!film.getUserLikes().contains(userId)) {
            log.warn("Пользователь {} не ставил лайк фильму {}", userId, filmId);
            throw new ValidationException("Пользователь не может удалить лайк не поставив его");
        }

        film.getUserLikes().remove(userId);
        film.setLikes(film.getLikes() - 1);
        log.info("Лайк от пользователя {} успешно удалён к фильму {}", userId, filmId);
    }

    @Override
    public List<Film> top10PopularMovies(Integer count) {
        log.info("Получаем топ {} популярных фильмов", count);
        return getAllFilms().stream()
                .sorted(Comparator.comparingInt(Film::getLikes).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    private long generationId() {
        long currentMaxId = films.keySet().stream().mapToLong(id -> id).max().orElse(0);
        return ++currentMaxId;
    }
}
