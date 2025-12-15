package mycode.masabiliardspring.dtos;

import java.util.List;

public record MasinaResponseListRequest(
        List<MasinaResponse> masiniList
) {}