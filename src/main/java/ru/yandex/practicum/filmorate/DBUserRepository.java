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
		final String sqlQuery = "select * from USERS where USER_ID = ?";
		final List<User> users = jdbcTemplate.query(sqlQuery, DBUserRepository::makeUser, id);
		if (users.size() != 1) {
			// TODO not found
		}
		return users.get(0);
	}

	static User makeUser(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getLong("USER_ID"),
				rs.getString("EMAIL"),
				rs.getString("LOGIN"),
				rs.getString("USER_NAME"),
				rs.getDate("BIRTHDAY").toLocalDate());
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
			stmt.setString(1, user.getEmail());
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
}
