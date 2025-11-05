package mycode.masabiliardspring.service;

import jakarta.transaction.Transactional;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaListRequest;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MasinaCommandServiceImpl implements MasinaCommandService {

    private MasinaRepository masinaRepository;
    private MasinaManualMapper mapper;

    public MasinaCommandServiceImpl(MasinaRepository masinaRepository,MasinaManualMapper mapper){
        this.mapper=mapper;
        this.masinaRepository=masinaRepository;
    }

    @Transactional
    @Override
    public List<MasinaListRequest> deleteAllByName(String name) {
        List<Masina>masinaList=masinaRepository.getAllMasina();

        return null;


    }

    @Transactional
    @Override
    public MasinaResponse createMasina(MasinaDto masinaDto) throws MasinaAlreadyExistsException {
        Optional<Masina> optionalMasina=masinaRepository.getMasinasByMarcaAndCuloare(masinaDto.marca(), masinaDto.culoare());
        if(optionalMasina.isPresent()){
            throw new MasinaAlreadyExistsException();
        }
       Masina masina= this.masinaRepository.save(mapper.mapMasinaDtoToMasina(masinaDto));
        return (mapper.mapMasinaToMasinaCreateResponse(masina));
    }

    @Transactional
    @Override
    public MasinaResponse deleteMasinaByNameAndColor(MasinaDto masinaDto) throws MasinaDoesntExistException {
        Optional<Masina>optionalMasina=masinaRepository.getMasinasByMarcaAndCuloare(masinaDto.marca(), masinaDto.culoare());
        if(!optionalMasina.isPresent()){
            throw new MasinaDoesntExistException();
        }
        this.masinaRepository.delete(optionalMasina.get());
        return (mapper.mapMasinaToMasinaCreateResponse(optionalMasina.get()));


    }


}
