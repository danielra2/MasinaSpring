package mycode.masabiliardspring.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MasinaDto(
        @NotBlank(message = "Marca is required")
        String marca,
        @Min(value = 1, message = "Marimea must be greater than 0")
        int marime,
        @NotBlank(message = "Culoare is required")
        String culoare
) {}