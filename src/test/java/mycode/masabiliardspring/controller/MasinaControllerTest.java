package mycode.masabiliardspring.controller;

import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.service.MasinaCommandService;
import mycode.masabiliardspring.service.MasinaQuerryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MasinaControllerTest {

    @Mock
    private MasinaCommandService commandService;
    @Mock
    private MasinaQuerryService querryService;

    private MasinaController controller;
    private MasinaDto dto;
    private MasinaResponse response;

    @BeforeEach
    void setUp() {
        controller =new MasinaController(querryService, commandService);
        dto =new MasinaDto("Audi",100,"Albastru");
        response =new MasinaResponse(1L,dto.marca(),dto.marime(),dto.culoare());
    }

    @Test
    void createMasinaDelegatesToServiceAndReturnsResponse() {
        when(commandService.createMasina(dto)).thenReturn(response);
        assertThat(controller.createMasina(dto)).isEqualTo(response);
    }

    @Test
    void getAllMasiniDelegatesToService() {
        MasinaResponseListRequest expected=new MasinaResponseListRequest(List.of(response));
        when(querryService.getAllMasini()).thenReturn(expected);
        assertThat(controller.getAllMasini()).isEqualTo(expected);
    }

    @Test
    void updateMasinaCallsCommandServiceWithCorrectArguments() {
        String currentMarca="Dacia";
        String currentCuloare="Alb";
        when(commandService.updateMasinaByMarcaAndCuloare(currentMarca, currentCuloare, dto)).thenReturn(response);
        assertThat(controller.updateMasina(currentMarca, currentCuloare, dto)).isEqualTo(response);
    }

    @Test
    void deleteMasinaDelegatesToService() {
        String marca="BMW";
        String culoare="Negru";
        controller.deleteMasina(marca, culoare);
        verify(commandService).deleteMasinaByMarcaAndCuloare(marca, culoare);
    }

    @Test
    void findByMarimeExactDelegatesToService() {
        int marimeCautata=100;
        MasinaIdMarcaCuloareInfo info=new MasinaIdMarcaCuloareInfo(1L,"Audi","Albastru");
        MasinaIdMarcaCuloareListRequest expected =new MasinaIdMarcaCuloareListRequest(List.of(info));
        when(querryService.findByMarimeExact(marimeCautata)).thenReturn(expected);
        assertThat(controller.findByMarimeExact(marimeCautata)).isEqualTo(expected);
    }

    @Test
    void findByCuloareDelegatesToService() {
        String culoareCautata="Albastru";
        MasinaMarcaCuloareInfo info=new MasinaMarcaCuloareInfo("Audi", "Albastru");
        MasinaMarcaCuloareListRequest expected=new MasinaMarcaCuloareListRequest(List.of(info));
        when(querryService.findByCuloare(culoareCautata)).thenReturn(expected);
        assertThat(controller.findByCuloare(culoareCautata)).isEqualTo(expected);
    }
}