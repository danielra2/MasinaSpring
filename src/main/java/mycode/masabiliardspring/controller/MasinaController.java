package mycode.masabiliardspring.controller;

import lombok.extern.slf4j.Slf4j;
import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.service.MasinaCommandService;
import mycode.masabiliardspring.service.MasinaQuerryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MasinaController implements MasinaControllerApi {

    private final MasinaQuerryService masinaQuerryService;
    private final MasinaCommandService masinaCommandService;

    public MasinaController(MasinaQuerryService masinaQuerryService,
                            MasinaCommandService masinaCommandService) {
        this.masinaQuerryService = masinaQuerryService;
        this.masinaCommandService = masinaCommandService;
    }

    @Override
    public MasinaResponse createMasina(MasinaDto masinaDto) {
        log.info("POST /api/masini/add-creare masina {} cu marimea {}", masinaDto.marca(), masinaDto.marime());
        return masinaCommandService.createMasina(masinaDto);
    }

    @Override
    public MasinaResponseListRequest getAllMasini() {
        log.info("GET /api/masini requested");

        MasinaResponseListRequest response=masinaQuerryService.getAllMasini();
        log.info("GET /api/masini - returnate {} rezultate", response.masiniList().size());

        return response;
    }

    @Override
    public MasinaResponse updateMasina(String currentMarca,String currentCuloare,MasinaDto newMasinaDto) {
        log.info("PUT /api/masini/{}/{} - actualizare catre {} {}",
                currentMarca,currentCuloare, newMasinaDto.marca(),newMasinaDto.culoare());

        return masinaCommandService.updateMasinaByMarcaAndCuloare(currentMarca, currentCuloare, newMasinaDto);
    }

    @Override
    public void deleteMasina(String marca,String culoare) {
        log.info("DELETE /api/masini/delete/{}/{}", marca, culoare);
        masinaCommandService.deleteMasinaByMarcaAndCuloare(marca, culoare);
    }

    @Override
    public MasinaIdMarcaCuloareListRequest findByMarimeExact(int marimeExacta) {
        log.info("GET /api/masini/marime/{} requested", marimeExacta);

        MasinaIdMarcaCuloareListRequest response = masinaQuerryService.findByMarimeExact(marimeExacta);
        log.info("GET /api/masini/marime/{} - returnate {} rezultate", marimeExacta, response.masiniList().size());

        return response;
    }

    @Override
    public MasinaMarcaCuloareListRequest findByCuloare(String culoare) {
        log.info("GET /api/masini/culoare/{} requested", culoare);

        MasinaMarcaCuloareListRequest response = masinaQuerryService.findByCuloare(culoare);
        log.info("GET /api/masini/culoare/{} - returnate {} rezultate", culoare, response.masiniList().size());

        return response;
    }
}