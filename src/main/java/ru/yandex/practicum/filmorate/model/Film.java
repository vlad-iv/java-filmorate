package ru.yandex.practicum.filmorate.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Film.
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
