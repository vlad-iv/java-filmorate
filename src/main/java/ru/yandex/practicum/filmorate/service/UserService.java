package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

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
