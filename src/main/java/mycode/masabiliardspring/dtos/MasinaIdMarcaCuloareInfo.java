package mycode.masabiliardspring.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasinaIdMarcaCuloareInfo {

    private Long id;
    private String marca;
    private String culoare;
}