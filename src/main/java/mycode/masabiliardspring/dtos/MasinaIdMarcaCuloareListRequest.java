package mycode.masabiliardspring.dtos;

import java.util.List;

public record MasinaIdMarcaCuloareListRequest(
        List<MasinaIdMarcaCuloareInfo> masiniList
) {}