package ru.yandex.practicum.filmorate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
public class UserService {

	@Autowired
	UserRepository repository;


	public User save(User user) {
		return repository.save(user);
	}

	public User get(long id) {
		final User user = repository.getById(id);
		if (user == null) {
			throw new NotFoundException("user with id=" + id + " not found");
		}
		return user;
	}


//	List<Film> getAll() {
//		List<Film> films = filmStrorage.getAll(); // mpa
//		for (Film film : films) {
//			genreStrorage.loadFilmGenre(film);
//		}
//		return films;
//	}

	/*

	 */
}
