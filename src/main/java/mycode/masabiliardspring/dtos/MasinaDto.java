package mycode.masabiliardspring.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasinaDto {

        @NotBlank(message = "Marca is required")
        private String marca;

        @Min(value = 1, message = "Marimea must be greater than 0")
        private int marime;

        @NotBlank(message = "Culoare is required")
        private String culoare;


}