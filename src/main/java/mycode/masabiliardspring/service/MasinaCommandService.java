package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;

public interface MasinaCommandService {

    MasinaResponse createMasina(MasinaDto masinaDto) throws MasinaAlreadyExistsException;
    MasinaResponse updateMasinaByMarcaAndCuloare(String currentMarca, String currentCuloare, MasinaDto newMasinaDto) throws MasinaDoesntExistException;
    MasinaResponse deleteMasinaByMarcaAndCuloare(String marca, String culoare) throws MasinaDoesntExistException;
}