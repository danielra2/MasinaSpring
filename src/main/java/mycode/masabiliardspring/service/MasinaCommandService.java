package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaListRequest;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;

import java.util.List;

public interface MasinaCommandService {
    List<MasinaListRequest> deleteAllByName(String name);
    MasinaResponse createMasina(MasinaDto masinaDto) throws MasinaAlreadyExistsException;
    MasinaResponse deleteMasinaByNameAndColor(MasinaDto masinaDto);
}
