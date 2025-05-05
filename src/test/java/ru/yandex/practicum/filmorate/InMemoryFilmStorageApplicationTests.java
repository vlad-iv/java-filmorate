package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class InMemoryFilmStorageApplicationTests {
    private InMemoryFilmStorage filmStorage;

    @BeforeEach
    public void setUp() {
        filmStorage = new InMemoryFilmStorage();
    }

    private Film createFilm(String name, String description, LocalDate releaseDate, long duration) {
        return Film.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .duration(duration)
                .build();
    }

    /**
     * Создание фильма.
     */
    @Test
    void shouldAddFilm() {
        Film film = createFilm("Тестовый фильм", "Описание тестового фильма", LocalDate.now(),
                120L);

        Film addFilm = filmStorage.addFilm(film);

        Assertions.assertNotNull(addFilm.getId(), "ID фильма не должен быть пустым");
        Assertions.assertEquals(film.getName(), addFilm.getName());
    }

    /**
     * Обновление фильма.
     */
    @Test
    void shouldUpdateFilm() {
        Film film = createFilm("Тестовый фильм", "Описание тестового фильма", LocalDate.now(),
                120L);

        Film addFilm = filmStorage.addFilm(film);
        addFilm.setName("Обновляем фильм");
        Film updateFilm = filmStorage.update(addFilm);

        Assertions.assertEquals("Обновляем фильм", updateFilm.getName());
    }

    /**
     * Получаем все фильмы.
     */
    @Test
    void shouldGetAllFilms() {
        Film film = createFilm("Тестовый фильм №1", "Описание тестового фильма №1", LocalDate.now(),
                120L);
        Film film2 = createFilm("Тестовый фильм №2", "Описание тестового фильма №2", LocalDate.now(),
                120L);
        Film film3 = createFilm("Тестовый фильм №3", "Описание тестового фильма №3", LocalDate.now(),
                120L);
        filmStorage.addFilm(film);
        filmStorage.addFilm(film2);
        filmStorage.addFilm(film3);

        List<Film> getListFilms = filmStorage.getAllFilms();

        Assertions.assertEquals(3, getListFilms.size());
    }

    /**
     * Получаем фильмы по ID.
     */
    @Test
    void shouldGetFilmById() {
        Film film = createFilm("Тестовый фильм №1", "Описание тестового фильма №1", LocalDate.now(),
                120L);
        filmStorage.addFilm(film);

        Optional<Film> filmById = filmStorage.findById(film.getId());

        Assertions.assertEquals(film.getId(), filmById.get().getId());
    }

    /**
     * Наименование фильма не должно быть пустым.
     */
    @Test
    void shouldntNameFilmToBeEmpty() {
        Film film = createFilm("", "Описание тестового фильма", LocalDate.now(),
                120L);

        // Если наименование фильма пустое, тогда выбрасываем исключение.
        Assertions.assertThrows(ValidationException.class, () -> {
            filmStorage.addFilm(film);
        });
    }

    /**
     * Длина описания фильма не должна превышать 200 символов.
     */
    @Test
    void shouldMaxLengthDescription200Char() {
        Film film = createFilm("", "Слишком длинное описание, которое превышает допустимую длину ........................." +
                        "...................." + ".........................................." +
                        ".....................................................", LocalDate.now(),
                120L);

        // Если описание превышает лимит в 200 символов, тогда выбрасываем исключение.
        Assertions.assertThrows(ValidationException.class, () -> {
            filmStorage.addFilm(film);
        });
    }

    /**
     * Дата релиза — не раньше 28 декабря 1895 года.
     */
    @Test
    void shouldReleaseDateNotBefore28December1985Year() {
        LocalDate localDate = LocalDate.of(1885, 12, 16);
        Film film = createFilm("", "Описание тестового фильма", localDate,
                120L);

        Assertions.assertThrows(ValidationException.class, () -> {
            filmStorage.addFilm(film);
        });
    }

    /**
     * Продолжительность фильма должна быть положительным числом.
     */
    @Test
    void shouldDurationFilmInPositive() {
        Film film = createFilm("", "Описание тестового фильма", LocalDate.now(),
                -120L);

        Assertions.assertThrows(ValidationException.class, () -> {
            filmStorage.addFilm(film);
        });
    }

    /**
     * Добавление и удаление лайка у фильма.
     */
    @Test
    void shouldAddAndDeleteLikeFilm() {
        Long userId = 1L;
        Film film = createFilm("Тестовый фильм", "Описание тестового фильма", LocalDate.now(),
                120L);
        filmStorage.addFilm(film);
        filmStorage.addLike(film.getId(), userId);
        Assertions.assertTrue(film.getUserLikes().contains(userId), "Лайк должен быть добавлен");
        Assertions.assertEquals(1, film.getUserLikes().size());
        Assertions.assertEquals(1, film.getLikes());

        filmStorage.deleteLike(film.getId(), userId);
        Assertions.assertFalse(film.getUserLikes().contains(userId), "Лайк должен быть удалён");
        Assertions.assertEquals(0, film.getUserLikes().size());
        Assertions.assertEquals(0, film.getLikes());
    }

    /**
     * Топ 10 популярных фильмов по количеству лайков.
     */
    @Test
    void shouldViewTop10Movies() {
        Film film = createFilm("Тестовый фильм", "Описание тестового фильма", LocalDate.now(),
                120L);
        Film film2 = createFilm("Тестовый фильм №2", "Описание тестового фильма №2", LocalDate.now(),
                120L);

        filmStorage.addFilm(film);
        filmStorage.addFilm(film2);
        film.setLikes(15);
        film2.setLikes(10);

        List<Film> popularFilms = filmStorage.top10PopularMovies(2);

        Assertions.assertEquals(2, popularFilms.size(), "Кол-во не должно превышать двух фильмов");
        Assertions.assertEquals("Тестовый фильм", popularFilms.get(0).getName());
        Assertions.assertEquals("Тестовый фильм №2", popularFilms.get(1).getName());
    }

}
