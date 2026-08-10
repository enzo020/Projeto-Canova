package Jar.Dto;


public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        String imageUrl,
        String lineId
) {}
