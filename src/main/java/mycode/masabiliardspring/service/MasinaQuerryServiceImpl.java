package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaListRequest;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MasinaQuerryServiceImpl implements MasinaQuerryService {

    private MasinaRepository masinaRepository;
    private MasinaManualMapper mapper;


    public MasinaQuerryServiceImpl(MasinaRepository masaBiliardRepository, MasinaManualMapper mapper){
        this.masinaRepository=masaBiliardRepository;
        this.mapper=mapper;

    }


    @Override

    public MasinaListRequest getAllMasaBiliard() {
        List<Masina> mese=masinaRepository.getAllMasina();
        return new MasinaListRequest(this.mapper.mapperMasinaListToMasinaDtoList(mese));
    }
}
