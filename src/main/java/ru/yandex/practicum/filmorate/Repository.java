package ru.yandex.practicum.filmorate;

import java.util.List;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface Repository<T extends BaseEntity> {
	T getById(long id);

	List<T> getAll();

	// TODO
}
