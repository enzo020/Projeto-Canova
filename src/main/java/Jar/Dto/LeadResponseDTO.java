package Jar.Dto;

import java.time.LocalDateTime;

public record LeadResponseDTO(
        Long id,
        String name,
        String contact,
        String message,
        LocalDateTime createdAt
) {}