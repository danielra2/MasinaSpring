package mycode.masabiliardspring.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.service.MasinaCommandService;
import mycode.masabiliardspring.service.MasinaQuerryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/masini")
@Slf4j
public class MasinaController {

    private final MasinaQuerryService masinaQuerryService;
    private final MasinaCommandService masinaCommandService;

    public MasinaController(MasinaQuerryService masinaQuerryService, MasinaCommandService masinaCommandService) {
        this.masinaQuerryService = masinaQuerryService;
        this.masinaCommandService = masinaCommandService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MasinaResponse createMasina(@Valid @RequestBody MasinaDto masinaDto) {
        log.info("POST /api/masini/add - creare masina {} cu marimea {}", masinaDto.marca(), masinaDto.marime());
        return masinaCommandService.createMasina(masinaDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MasinaResponseListRequest getAllMasini() {
        log.info("GET /api/masini requested");
        MasinaResponseListRequest response = masinaQuerryService.getAllMasini();
        log.info("GET /api/masini - returnate {} rezultate", response.masinaResponseList().size());
        return response;
    }

    @PutMapping("/{currentMarca}/{currentCuloare}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaResponse updateMasina(
            @PathVariable String currentMarca,
            @PathVariable String currentCuloare,
            @Valid @RequestBody MasinaDto newMasinaDto) {
        log.info("PUT /api/masini/{}/{} - actualizare catre {} {}", currentMarca, currentCuloare, newMasinaDto.marca(), newMasinaDto.culoare());
        return masinaCommandService.updateMasinaByMarcaAndCuloare(currentMarca, currentCuloare, newMasinaDto);
    }

    @DeleteMapping("/delete/{marca}/{culoare}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMasina(
            @PathVariable String marca,
            @PathVariable String culoare) {
        log.info("DELETE /api/masini/delete/{}/{}", marca, culoare);
        masinaCommandService.deleteMasinaByMarcaAndCuloare(marca, culoare);
    }

    @GetMapping("/marime/{marimeExacta}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaIdMarcaCuloareListRequest findByMarimeExact(@PathVariable int marimeExacta) {
        log.info("GET /api/masini/marime/{} requested", marimeExacta);
        MasinaIdMarcaCuloareListRequest response = masinaQuerryService.findByMarimeExact(marimeExacta);
        log.info("GET /api/masini/marime/{} - returnate {} rezultate", marimeExacta, response.masinaIdMarcaCuloareResponseList().size());
        return response;
    }

    @GetMapping("/culoare/{culoare}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaMarcaCuloareListRequest findByCuloare(@PathVariable String culoare) {
        log.info("GET /api/masini/culoare/{} requested", culoare);
        MasinaMarcaCuloareListRequest response = masinaQuerryService.findByCuloare(culoare);
        log.info("GET /api/masini/culoare/{} - returnate {} rezultate", culoare, response.masinaMarcaCuloareResponseList().size());
        return response;
    }
}