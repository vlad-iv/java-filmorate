package ru.yandex.practicum.filmorate;

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
		return repository.getById(id);
	}
}
