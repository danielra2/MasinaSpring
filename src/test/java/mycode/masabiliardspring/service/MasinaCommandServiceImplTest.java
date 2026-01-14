package mycode.masabiliardspring.service;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.hibernate.validator.internal.metadata.aggregated.FieldCascadable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

// IMPORTURI CORECTE
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MasinaCommandServiceImplTest {

    @Mock
    private MasinaRepository repository;
    @Mock
    private MasinaManualMapper mapper;
    private MasinaCommandService masinaCommandService;

    @BeforeEach
    void setup(){
        masinaCommandService = new MasinaCommandServiceImpl(repository, mapper);
    }

    @Test
    void createMasinaThrowsWhenMasinaAlreadyExists() {
        MasinaDto dto = new MasinaDto("Audi", 100, "Albastru");
        when(repository.findByMarcaAndCuloare(dto.marca(), dto.culoare())).thenReturn(Optional.of(new Masina()));
        assertThatThrownBy(()->masinaCommandService.createMasina(dto)).isInstanceOf(MasinaAlreadyExistsException.class);
        verify(repository, never()).save(any());
    }

    @Test
    void createMasinaPersistsMappedEntity() {
        MasinaDto dto =new MasinaDto("Audi", 100, "Albastru");
        Masina entity = new Masina();
        MasinaResponse response =new MasinaResponse(1L, "Audi", 100, "Albastru");
        when(repository.findByMarcaAndCuloare(dto.marca(), dto.culoare())).thenReturn(Optional.empty());
        when(mapper.mapMasinaDtoToMasina(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.mapMasinaToMasinaResponse(entity)).thenReturn(response);
        MasinaResponse result = masinaCommandService.createMasina(dto);
        assertThat(result).isEqualTo(response);
        verify(repository).save(entity);
    }
    @Test
    void updateMasinaByMarcaAndCuloare_ThrowsException_WhenMasinaDoesNotExist() {
        String marca = "Audi";
        String culoare = "Negru";
        MasinaDto dto = new MasinaDto("Audi A4", 110, "Gri");
        when(repository.findByMarcaAndCuloare(marca, culoare)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> masinaCommandService.updateMasinaByMarcaAndCuloare(marca, culoare, dto)).isInstanceOf(MasinaDoesntExistException.class);
    }
    @Test
    void updateMasinaDelegatesToMapperAndSaves(){

        MasinaDto dto=new MasinaDto("Audi A5",110,"galben");//dto cu care editam obiectul
        Masina entity= new Masina(15L,"Audi A4",100,"Gri");//entitatea ce db

        Masina entityAfterUpdate= new Masina(15L,dto.marca(),dto.marime(),dto.culoare());//entitatea ce db
        when(repository.findByMarcaAndCuloare("Audi A4","Gri")).thenReturn(Optional.of(entity));

        masinaCommandService.updateMasinaByMarcaAndCuloare("Audi A4","Gri",dto);
        verify(repository).save(entityAfterUpdate);



    }
    @Test
    void deleteMasinaByMarcaAndCuloare_ThrowsException_WhenMasinaDoesNotEXIST(){
        String marca="Audi";
        String culoare="Negru";
        MasinaDto dto=new MasinaDto("Audi A4",110,"Gri");
        when(repository.findByMarcaAndCuloare(marca,culoare)).thenReturn(Optional.empty());
        assertThatThrownBy(()->masinaCommandService.deleteMasinaByMarcaAndCuloare(marca,culoare)).isInstanceOf(MasinaDoesntExistException.class);
        verify(repository, never()).save(any());

    }
}