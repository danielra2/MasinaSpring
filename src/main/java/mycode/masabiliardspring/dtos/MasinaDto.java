package mycode.masabiliardspring.dtos;

import jakarta.validation.constraints.NotBlank;

public record MasinaDto(
        @NotBlank(message = "marca is required")
        String marca,
        int marime,

        @NotBlank(message = "culoare is required")
        String culoare

) {
}
