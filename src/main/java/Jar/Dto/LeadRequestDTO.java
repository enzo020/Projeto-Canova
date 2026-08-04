package Jar.Dto;

import jakarta.validation.constraints.NotBlank;

public record LeadRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Contato é obrigatório")
        String contact,

        @NotBlank(message = "Mensagem é obrigatória")
        String message
) {}