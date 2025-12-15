package mycode.masabiliardspring.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasinaMarcaCuloareListRequest {
    private List<MasinaMarcaCuloareInfo> masiniList;
}