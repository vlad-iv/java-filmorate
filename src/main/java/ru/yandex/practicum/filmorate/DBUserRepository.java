package ru.yandex.practicum.filmorate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Repository
public class DBUserRepository implements UserRepository {

	private final JdbcTemplate jdbcTemplate;

	public DBUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addFriend(long userId, long friend) {
		// TODO
	}

	@Override
	public User getById(long id) {
		final String sqlQuery = "select USER_ID, LOGIN, BIRTHDAY, USER_NAME " +
				"from USERS Join MPA ON ()" +
				"where USER_ID = ?";
		final List<User> users = jdbcTemplate.query(sqlQuery, DBUserRepository::makeUser, id);
		if (users.size() != 1) {
			return null;
		}
//		final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sqlQuery);
//		final Object value = maps.get(0).values().iterator().next();
//		final String sqlSingle = "select COUNT(USER_ID) " +
//				"from USERS " +
//				"where LOGIN = ?";
//		Integer countLogins = jdbcTemplate.queryForObject(sqlSingle, Integer.class);
//
//		jdbcTemplate.queryForList(sqlQuery);
//		rs.getLong("USER_ID")
		return users.get(0);
	}

	static User makeUser(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getLong("USER_ID"),
				rs.getString("EMAIL"),
				rs.getString("LOGIN"),
				rs.getString("USER_NAME"),
				rs.getDate("BIRTHDAY").toLocalDate(),
				new Mpa(rs.getLong("MPA.MPA_ID"), rs.getString("MPA.NAME"))
		);
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public User save(User user) {
		String sqlQuery = "insert into USERS (EMAIL, LOGIN, USER_NAME, BIRTHDAY) values (?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"USER_ID"});
			stmt.setString(1, user.getEmailNew());
			stmt.setString(2, user.getLogin());
			stmt.setString(3, user.getName());
			final LocalDate birthday = user.getBirthday();
			if (birthday == null) {
				stmt.setNull(4, Types.DATE);
			} else {
				stmt.setDate(4, Date.valueOf(birthday));
			}
			return stmt;
		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	@Override
	public void update(User data) {

	}

	void setFilmGenre(Film film) {
		// SQL DELETE FROM film_genres where film_id = film.get()
		if (film.genres == null || film.genres.isEmpty()) {
			return;
		}
		for (Genre genre : film.genres) {
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

//	void loadFilmGenre(List<Film> films) {
//		final List<Long> ids = films.stream().map(Film::getId).collect(Collectors.toList());
//		final Map<Long, Film> filmMap = films.stream()
//				.collect(
//						Collectors.toMap(film -> film.id, film -> film, (a, b) -> b));
//
//		// SELECT film_id , genres.* FROM genres WHERE film_id in (:ids)
//		filmMap.get(film_id).addGenre(new Genre());
//	}

	// Общие друзья
	// 1 загрузить всех и програмно - самый медленный
	// 2 intesect  - вложенный подзапрос - медленный запрос
	// 3 join

	// User

}
