package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaIdMarcaCuloareListRequest;
import mycode.masabiliardspring.dtos.MasinaListRequest;
import mycode.masabiliardspring.dtos.MasinaListResponsePageable;
import mycode.masabiliardspring.dtos.MasinaMarcaCuloareListRequest;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;

public interface MasinaQuerryService {

    MasinaListResponsePageable getAllMasini(int page, int size);
    MasinaIdMarcaCuloareListRequest findByMarimeExact(int marime) throws NoMasinaFoundException;
    MasinaMarcaCuloareListRequest findByCuloare(String culoare) throws NoMasinaFoundException;

}
