package Jar.Dto;

public record LoginResponseDTO(
        String token,
        String username,
        String role
) {}