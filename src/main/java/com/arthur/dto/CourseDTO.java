package com.arthur.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Se quiser criar outro DTO
public record CourseDTO(
                @JsonProperty("_id") Long id,
                @NotBlank @NotNull @Length(min = 3, max = 100) String name,
                @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category) {

}
