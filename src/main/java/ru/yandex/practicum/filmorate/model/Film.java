package ru.yandex.practicum.filmorate.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Getter
@Setter
@NoArgsConstructor
public class Film {
	Integer id;
	@NotBlank
	String name;
	@JsonIgnore
	Set<Integer> userIds = new HashSet<>();
}
