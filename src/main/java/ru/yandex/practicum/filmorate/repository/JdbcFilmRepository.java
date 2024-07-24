package ru.yandex.practicum.filmorate.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.Film;

@Repository
@RequiredArgsConstructor
public class JdbcFilmRepository implements FilmRepository {
	private final NamedParameterJdbcOperations jdbc;

	@Override
	public Optional<Film> getById(long id) {
		// select from films join MPA join GENRES where film_id = :id
		// ResultSetExtractor
//		ResultSet rs;
//		Film film = null;
//		while (rs.next()) {
//			if (film == null) {
//				film = new Film(); // заполняем все поля , а жанры пустые
//			}
//			// добавляем жанры
//		 }
//		return film;
		return Optional.empty();
	}

	@Override
	public Film save(Film film) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		HashMap<String, Object> params = new HashMap<>();
		params.put("name", film.getName());
//		params.put("desc", film.getDesc());
//		jdbc.update("INSERT INTO FILMS (desc, name)  VALUES (:desc, :name )", params, keyHolder, new String[]{"film_id"});
		// Создать связи фильм - жанры
		// batch
		film.setId(keyHolder.getKeyAs(Long.class));
		return film;
	}

	@Override
	public void update(Film data) {
		// Обновить фильм и его рейтинг UPDATE
		// Удаляем связи фильмы-жанры DELETE
		// Добавить связи  фильмы-жанры через батч INSERT
		// batch jdbc
//		jdbc.batchUpdate()
	}


	@Override
	public List<Film> getAll() {
		// 1. Получить все жанры (их мало)
		// 2. Получить все фильмы с рейтингом
		// 3. Получить связи жанры - фильмы static record GenreRelation(film_id, genre_id)
		// Объединить
		return List.of();
	}

	@Override
	public void addLike(long filmId, long userId) {
		// select or update or insert = merge

	}

	@Override
	public void deleteLike(long filmId, long userId) {

	}

	@Override
	public List<Film> getTopPopular(int count) {
		return List.of();
	}

	record GenreRelation(long filmId, long genreId) {

	}
}
