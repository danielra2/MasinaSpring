package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaIdMarcaCuloareListRequest;
import mycode.masabiliardspring.dtos.MasinaMarcaCuloareListRequest;
import mycode.masabiliardspring.dtos.MasinaResponseListRequest;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;

public interface MasinaQuerryService {

    MasinaResponseListRequest getAllMasini();
    MasinaIdMarcaCuloareListRequest findByMarimeExact(int marime) throws NoMasinaFoundException;
    MasinaMarcaCuloareListRequest findByCuloare(String culoare) throws NoMasinaFoundException;

}