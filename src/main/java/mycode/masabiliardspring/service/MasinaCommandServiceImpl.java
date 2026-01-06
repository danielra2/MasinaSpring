package mycode.masabiliardspring.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
@Slf4j
public class MasinaCommandServiceImpl implements MasinaCommandService {

    private final MasinaRepository masinaRepository;
    private final MasinaManualMapper mapper;

    public MasinaCommandServiceImpl(MasinaRepository masinaRepository,MasinaManualMapper mapper){
        this.mapper=mapper;
        this.masinaRepository=masinaRepository;
    }

    @Transactional
    @Override
    public MasinaResponse createMasina(MasinaDto masinaDto) {
        Optional<Masina> optionalMasina = masinaRepository.findByMarcaAndCuloare(masinaDto.marca(), masinaDto.culoare());

        if(optionalMasina.isPresent()){
            throw new MasinaAlreadyExistsException();
        }

        Masina masina = this.masinaRepository.save(mapper.mapMasinaDtoToMasina(masinaDto));

        return mapper.mapMasinaToMasinaResponse(masina);
    }

    @Transactional
    @Override
    public MasinaResponse updateMasinaByMarcaAndCuloare(String currentMarca, String currentCuloare, MasinaDto newMasinaDto) {
        Optional<Masina> optionalMasina = masinaRepository.findByMarcaAndCuloare(currentMarca, currentCuloare);

        if(!optionalMasina.isPresent()){
            throw new MasinaDoesntExistException();
        }

        Masina existingMasina = optionalMasina.get();

        existingMasina.setMarca(newMasinaDto.marca());
        existingMasina.setCuloare(newMasinaDto.culoare());
        existingMasina.setMarime(newMasinaDto.marime());

        existingMasina = masinaRepository.save(existingMasina);

        return mapper.mapMasinaToMasinaResponse(existingMasina);
    }
    @Transactional
    @Override
    public MasinaResponse deleteMasinaByMarcaAndCuloare(String marca, String culoare) {
        Optional<Masina> optionalMasina = masinaRepository.findByMarcaAndCuloare(marca, culoare);

        if(!optionalMasina.isPresent()){
            throw new MasinaDoesntExistException();
        }

        Masina masinaToDelete = optionalMasina.get();
        this.masinaRepository.delete(masinaToDelete);

        return mapper.mapMasinaToMasinaResponse(masinaToDelete);
    }
}