package Jar.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//tudo que entra
public record ProductRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        String description,

        String imageUrl,

        @NotNull(message = "Linha é obrigatória")
        Long lineId
) {}
