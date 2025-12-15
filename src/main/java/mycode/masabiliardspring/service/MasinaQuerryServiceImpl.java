package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasinaQuerryServiceImpl implements MasinaQuerryService {

    private final MasinaRepository masinaRepository;
    private final MasinaManualMapper mapper;

    public MasinaQuerryServiceImpl(MasinaRepository masinaRepository, MasinaManualMapper mapper){
        this.masinaRepository=masinaRepository;
        this.mapper=mapper;
    }

    @Override
    public MasinaResponseListRequest getAllMasini() {
        List<Masina> masinaList = masinaRepository.findAll();
        List<MasinaResponse> masinaResponseList = mapper.mapMasinaListToResponseList(masinaList);
        return new MasinaResponseListRequest(masinaResponseList);
    }
    @Override
    public MasinaIdMarcaCuloareListRequest findByMarimeExact(int marime) throws NoMasinaFoundException {
        List<Masina> masinaList = masinaRepository.findByMarime(marime);

        if(masinaList.isEmpty()){
            throw new NoMasinaFoundException();
        }

        List<MasinaIdMarcaCuloareInfo> infoList = mapper.mapMasinaListToIdMarcaCuloareInfoList(masinaList);

        return new MasinaIdMarcaCuloareListRequest(infoList);
    }

    @Override
    public MasinaMarcaCuloareListRequest findByCuloare(String culoare) throws NoMasinaFoundException {
        List<Masina> masinaList = masinaRepository.findByCuloare(culoare);
        if(masinaList.isEmpty()){
            throw new NoMasinaFoundException();
        }
        List<MasinaMarcaCuloareInfo>infoList = mapper.mapMasinaListToMarcaCuloareInfoList(masinaList);
        return new MasinaMarcaCuloareListRequest(infoList);
    }
}