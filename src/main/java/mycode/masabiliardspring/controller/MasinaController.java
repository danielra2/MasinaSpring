package mycode.masabiliardspring.controller;

import jakarta.validation.Valid;
import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaIdMarcaCuloareListRequest;
import mycode.masabiliardspring.dtos.MasinaMarcaCuloareListRequest;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.dtos.MasinaResponseListRequest;
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
        return masinaCommandService.createMasina(masinaDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MasinaResponseListRequest getAllMasini() {
        return masinaQuerryService.getAllMasini();
    }

    @PutMapping("/{currentMarca}/{currentCuloare}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaResponse updateMasina(
            @PathVariable String currentMarca,
            @PathVariable String currentCuloare,
            @Valid @RequestBody MasinaDto newMasinaDto) {
        return masinaCommandService.updateMasinaByMarcaAndCuloare(currentMarca, currentCuloare, newMasinaDto);
    }

    @DeleteMapping("/delete/{marca}/{culoare}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMasina(
            @PathVariable String marca,
            @PathVariable String culoare) {
        masinaCommandService.deleteMasinaByMarcaAndCuloare(marca, culoare);
    }

    @GetMapping("/marime/{marimeExacta}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaIdMarcaCuloareListRequest findByMarimeExact(@PathVariable int marimeExacta) {
        return masinaQuerryService.findByMarimeExact(marimeExacta);
    }

    @GetMapping("/culoare/{culoare}")
    @ResponseStatus(HttpStatus.OK)
    public MasinaMarcaCuloareListRequest findByCuloare(@PathVariable String culoare) {
        return masinaQuerryService.findByCuloare(culoare);
    }
}