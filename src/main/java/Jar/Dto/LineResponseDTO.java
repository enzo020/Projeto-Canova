package Jar.Dto;

import jakarta.validation.constraints.NotBlank;

public record LineResponseDTO(
        Long lineId,
        String name)
{}

