package Jar.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LineRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotNull(message = "Linha é obrigatória")
        Long lineId
) {}
