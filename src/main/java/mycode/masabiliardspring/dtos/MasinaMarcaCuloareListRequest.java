package mycode.masabiliardspring.dtos;

import java.util.List;

public record MasinaMarcaCuloareListRequest(
        List<MasinaMarcaCuloareInfo> masiniList
) {}