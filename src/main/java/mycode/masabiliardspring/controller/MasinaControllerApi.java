package mycode.masabiliardspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mycode.masabiliardspring.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/masini")
@Tag(name = "Masini", description = "Operatii de gestionare a masinilor")
public interface MasinaControllerApi {

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adauga o masina", description = "creeaza o noua masina in baza de date")
    MasinaResponse createMasina(@Valid @RequestBody MasinaDto masinaDto);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista masini", description = "returneaza toate masinile disponibile")
    MasinaResponseListRequest getAllMasini();

    @PutMapping("/{currentMarca}/{currentCuloare}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "actualizeaza masina", description = "modifica datele unei masini identificate prin marca si culoare")
    MasinaResponse updateMasina(
            @Parameter(description = "marca actuala") @PathVariable String currentMarca,
            @Parameter(description = "culoarea actuala") @PathVariable String currentCuloare,
            @Valid @RequestBody MasinaDto newMasinaDto);

    @DeleteMapping("/delete/{marca}/{culoare}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "tterge masina", description = "ellimina o masina din sistem")
    void deleteMasina(
            @PathVariable String marca,
            @PathVariable String culoare);

    @GetMapping("/marime/{marimeExacta}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "cauta dupa marime", description = "gaseste masini cu o anumita marime")
    MasinaIdMarcaCuloareListRequest findByMarimeExact(@PathVariable int marimeExacta);

    @GetMapping("/culoare/{culoare}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cauta dupa culoare", description = "Gaseste toate masinile de o anumita culoare")
    MasinaMarcaCuloareListRequest findByCuloare(@PathVariable String culoare);
}