package Jar.Dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank(message = "Usuário é obrigatório")
        String username,

        @NotBlank(message = "Senha é obrigatória")
        String password,

        @NotBlank(message = "Role é obrigatória")
        String role
) {}