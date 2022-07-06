package ru.yandex.practicum.filmorate;

import java.util.HashMap;
import java.util.List;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class AbstractRepository<T extends BaseEntity> implements Repository<T> {
	private final HashMap<Long, BaseEntity> data = new HashMap<>();
	private long idGenerator;

	@Override
	public T getById(long id) {
		return null;
	}

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public T save(T data) {
		return null;
	}

	@Override
	public void update(T data) {

	}
}
