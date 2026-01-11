package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MasinaCommandServiceImplTest {

    @Mock
    private MasinaRepository repository;
    private MasinaManualMapper mapper;
    @Mock
    private MasinaCommandService masinaCommandService;


    @BeforeEach
    void setup(){
        masinaCommandService=new MasinaCommandServiceImpl(repository,new MasinaManualMapper());
    }
    @Test
    void createMasinaPersistsMappedEntity(){
        MasinaDto dto=new MasinaDto("Audi",100,"albastru");
        Masina entity;

    }





}
