package mycode.masabiliardspring.service;


import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MasinaQueryServiceImplTest {
    @Mock
    private MasinaRepository repository;

@Mock
   private  MasinaManualMapper mapper;
    private MasinaQuerryService service;

    @BeforeEach
    void setup(){
        service=new MasinaQuerryServiceImpl(repository,new MasinaManualMapper());
    }

    @Test
    void findByMarimeExact_whenRepositoryReturnsMasini_thenMapsToMasinaIdMarcaCuloareInfoList(){
        //preconditie
        Masina m1 = new Masina(100L, "Dacia Logan", 4300, "Alb");
        Masina m2 = new Masina(101L, "Toyota Corolla", 4600, "Albastru");
        Masina m3 = new Masina(102L, "BMW Seria 3", 4700, "Negru");
        Masina m4 = new Masina(103L, "Volkswagen Golf", 4200, "Gri");
        Masina m5 = new Masina(104L, "Tesla Model 3", 4690, "Rosu");

        List<Masina>masinaList=new ArrayList<>();
        masinaList.add(m1);
        masinaList.add(m2);
        masinaList.add(m3);
        masinaList.add(m4);
        masinaList.add(m5);

        when(repository.findByMarime(4300)).thenReturn(masinaList);

        MasinaIdMarcaCuloareInfo m6 = new MasinaIdMarcaCuloareInfo(1L, "Dacia Logan", "Alb");
        MasinaIdMarcaCuloareInfo m7 = new MasinaIdMarcaCuloareInfo(2L, "Toyota Corolla", "Albastru");
        MasinaIdMarcaCuloareInfo m8 = new MasinaIdMarcaCuloareInfo(3L, "BMW Seria 3", "Negru");
        MasinaIdMarcaCuloareInfo m9 = new MasinaIdMarcaCuloareInfo(4L, "Volkswagen Golf", "Gri");
        MasinaIdMarcaCuloareInfo m10 = new MasinaIdMarcaCuloareInfo(5L, "Tesla Model 3", "Rosu");




        //actiune+verificare
        assertThat(service.findByMarimeExact(4300).masiniList().contains(m6)).isTrue();
        assertThat(service.findByMarimeExact(4300).masiniList().contains(m7)).isTrue();
        assertThat(service.findByMarimeExact(4300).masiniList().contains(m8)).isTrue();
        assertThat(service.findByMarimeExact(4300).masiniList().contains(m9)).isTrue();
        assertThat(service.findByMarimeExact(4300).masiniList().contains(m10)).isTrue();

    }
    @Test
    void testfindByMarimeExact_whenRepositoryReturnsEmpty_thenThrowsNoMasinaFoundException(){
        when(repository.findByMarime(10)).thenReturn(List.of());
        assertThatThrownBy(()->service.findByMarimeExact(10)).isInstanceOf(NoMasinaFoundException.class);

    }

    @Test
    void findByCuloare_whenRepositoryReturnsMasini_thenMapsToMasinaIdMarcaCuloareInfoList(){
        Masina m1 = new Masina(100L, "Dacia Logan", 4300, "Alb");
        Masina m2 = new Masina(101L, "Toyota Corolla", 4600, "Albastru");
        Masina m3 = new Masina(102L, "BMW Seria 3", 4700, "Negru");
        Masina m4 = new Masina(103L, "Volkswagen Golf", 4200, "Gri");
        Masina m5 = new Masina(104L, "Tesla Model 3", 4690, "Rosu");

        List<Masina>masinaList=new ArrayList<>();
        masinaList.add(m1);
        masinaList.add(m2);
        masinaList.add(m3);
        masinaList.add(m4);
        masinaList.add(m5);

        when(repository.findByCuloare("Alb")).thenReturn(masinaList);

        MasinaMarcaCuloareInfo m6 = new MasinaMarcaCuloareInfo( "Dacia Logan", "Alb");
        MasinaMarcaCuloareInfo m7 = new MasinaMarcaCuloareInfo( "Toyota Corolla", "Albastru");
        MasinaMarcaCuloareInfo m8 = new MasinaMarcaCuloareInfo( "BMW Seria 3", "Negru");
        MasinaMarcaCuloareInfo m9 = new MasinaMarcaCuloareInfo( "Volkswagen Golf", "Gri");
        MasinaMarcaCuloareInfo m10 = new MasinaMarcaCuloareInfo( "Tesla Model 3", "Rosu");


        assertThat(service.findByCuloare("Alb").masiniList().contains(m6)).isTrue();
        assertThat(service.findByCuloare("Alb").masiniList().contains(m7)).isTrue();
        assertThat(service.findByCuloare("Alb").masiniList().contains(m8)).isTrue();
        assertThat(service.findByCuloare("Alb").masiniList().contains(m9)).isTrue();
        assertThat(service.findByCuloare("Alb").masiniList().contains(m10)).isTrue();

    }

    @Test
    void testFindByCuloare_WhenRepositoryReturnsEmpty_ThenThrowsNoMasinaFoundException(){
        when(repository.findByCuloare("Alb")).thenReturn(List.of());
        assertThatThrownBy(()->service.findByCuloare("Alb")).isInstanceOf(NoMasinaFoundException.class);
    }




}
