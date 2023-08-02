package ru.yandex.practicum.filmorate.model;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class BaseEntity {
	Long id;

	public BaseEntity() {
	}

	public BaseEntity(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
