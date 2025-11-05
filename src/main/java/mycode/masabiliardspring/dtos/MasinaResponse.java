package mycode.masabiliardspring.dtos;

import jakarta.validation.constraints.NotBlank;

public record MasinaResponse(
        @NotBlank(message = "id is required")
        Long id,
        @NotBlank(message = "marca is required")
        String marca,
        int marime,
        @NotBlank(message = "culoare is required")
        String culoare
) {


}
