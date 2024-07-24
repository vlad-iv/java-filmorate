package ru.yandex.practicum.filmorate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.User;

/**
 * JDBC User Repository.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

//	private final JdbcTemplate jdbcTemplate; // неверно
//	private final JdbcOperations jdbcOperations; // хорошо, но ...

	private final NamedParameterJdbcOperations jdbc; // рекомендуется

	static User makeUser(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getLong("USER_ID"),
				rs.getString("LOGIN"),
				rs.getString("EMAIL"),
				rs.getDate("BIRTHDAY").toLocalDate()
//				new Mpa(rs.getLong("MPA.MPA_ID"), rs.getString("MPA.NAME")
		);
	}

	@Override
	public void addFriend(long userId, long friendId) {
//		jdbc.getJdbcOperations().update("INSERT INTO FRIENDS(USER_ID, FRIEND_ID) VALUES (?, ?)", userId, friendId);

		jdbc.update("INSERT INTO FRIENDS(USER_ID, FRIEND_ID) VALUES ( :userId, :friendId)",
				Map.of("userId", userId,
						"friendId", friendId));
		// TODO
	}

	@Override
	public void deleteFriend(long userId, long friend) {
		// TODO
	}

	@Override
	public void update(User user) {

	}

	@Override
	public Optional<User> getById(long id) {
		final String sqlQuery = "select USER_ID, EMAIL, LOGIN, BIRTHDAY, USER_NAME " +
				"from USERS " +
				"where USER_ID = ? ";
		final List<User> users = jdbc.getJdbcOperations().query(sqlQuery, JdbcUserRepository::makeUser, id);
//		final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sqlQuery);
//		final Object value = maps.get(0).values().iterator().next();
//		final String sqlSingle = "select COUNT(USER_ID) " +
//				"from USERS " +
//				"where LOGIN = ?";
//		Integer countLogins = jdbcTemplate.queryForObject(sqlSingle, Integer.class);
//
//		jdbcTemplate.queryForList(sqlQuery);
//		rs.getLong("USER_ID")
		return users.stream().findFirst();
	}

	@Override
	public List<User> getAll() {
		final String sqlQuery = "select USER_ID, LOGIN, BIRTHDAY, USER_NAME " +
				"from USERS ";
		return jdbc.query(sqlQuery, new UserRowMapper());
	}

	@Override
	public User save(User user) {
		String sqlQuery = "insert into USERS (EMAIL, LOGIN, USER_NAME, BIRTHDAY) values (:email, :login, :name, :birthday)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("email", user.getEmail());
		map.addValue("name", user.getName());
		map.addValue("birthday", user.getBirthday());
		jdbc.update(sqlQuery, map, keyHolder);
		user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return user;
	}

	void setFilmGenre(Film film) {
		// SQL DELETE FROM film_genres where film_id = film.get()
		if (film.getGenres() == null || film.getGenres().isEmpty()) {
			return;
		}
		for (Genre genre : film.getGenres()) {
			// SQL INSERT INTO film_genres(film_id, genre_id) values (?,?)
			// INSERT INTO film_genres(film_id, genre_id) values (?,?);
			// INSERT INTO film_genres(film_id, genre_id) values (?,?);
		}
		// Или сделать через batch

	}

	void loadFilmGenre(Film film) {
//		film.genres = // SQL SELECT * FROM film_genres  where film_id = film.get()
	}

	void loadFilmGenreSlow(List<Film> films) {
		for (Film film : films) {
			loadFilmGenre(film);
		}
	}

	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getLong("USER_ID"),
					rs.getString("EMAIL"),
//					rs.getString("LOGIN"),
					rs.getString("USER_NAME"),
					rs.getDate("BIRTHDAY").toLocalDate()
//					/*new Mpa(rs.getLong("MPA.MPA_ID"), rs.getString("MPA.NAME")*/ null
			);
		}
	}

//	void loadFilmGenre(List<Film> films) {
//		final List<Long> ids = films.stream().map(Film::getId).collect(Collectors.toList());
//
//		// SELECT film_id , genres.* FROM genres WHERE film_id in (:ids)

//		final Map<Long, Film> filmMap = films.stream()
//				.collect(
//						Collectors.toMap(film -> film.id, film -> film, (a, b) -> b));

//		filmMap.get(film_id).addGenre(new Genre());
//	}

	// Общие друзья
	// 1 загрузить всех и програмно - самый медленный
	// 2 intesect  - вложенный подзапрос - медленный запрос
	// 3 join

	// User

}
