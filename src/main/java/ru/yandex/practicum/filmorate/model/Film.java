package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Film.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Film {
    Long id;
    @NotBlank
    String name;
    @Size(max = 200, message = "Максимальная длина описания — 200 символов")
    String description;
    @NotNull
    @PastOrPresent(message = "Дата релиза не может быть в будущем")
    LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма должна быть положительным числом")
    Long duration;
    @Builder.Default
    Set<Long> userLikes = new HashSet<>();
    int likes;
}
