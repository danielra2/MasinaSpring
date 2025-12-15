package mycode.masabiliardspring.mappers;

import mycode.masabiliardspring.dtos.*;
import mycode.masabiliardspring.model.Masina;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MasinaManualMapper {

    // ******************************************************
    // Mapare 1: DTO -> Entitate (Pentru operatii CREATE/UPDATE)
    // ******************************************************

    public Masina mapMasinaDtoToMasina(MasinaDto dto) {
        Objects.requireNonNull(dto, "dto is null");
        var e = new Masina();
        e.setMarca(trim(dto.getMarca()));
        e.setMarime(dto.getMarime()); // Folosim Marime
        e.setCuloare(trim(dto.getCuloare()));
        return e;
    }

    // ******************************************************
    // Mapare 2: Entitate -> DTO (Pentru operatii READ/RESPONSE)
    // ******************************************************

    public MasinaResponse mapMasinaToMasinaResponse(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return MasinaResponse.builder()
                .id(e.getId())
                .marca(nvl(e.getMarca()))
                .marime(e.getMarime()) // Folosim Marime
                .culoare(nvl(e.getCuloare()))
                .build();
    }

    // ******************************************************
    // Mapare 3: Listă de Entități -> Listă de DTO-uri (Pentru paginare/interogări)
    // ******************************************************

    // Pentru MasinaListResponsePageable (List<MasinaResponse>)
    public List<MasinaResponse> mapMasinaListToResponseList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMasinaResponse)
                .toList();
    }

    // Pentru findByMarimeExact (returnează ID, Marca, Culoare)
    public List<MasinaIdMarcaCuloareInfo> mapMasinaListToIdMarcaCuloareInfoList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToIdMarcaCuloareInfo)
                .toList();
    }

    private MasinaIdMarcaCuloareInfo mapMasinaToIdMarcaCuloareInfo(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return MasinaIdMarcaCuloareInfo.builder()
                .id(e.getId())
                .marca(nvl(e.getMarca()))
                .culoare(nvl(e.getCuloare()))
                .build();
    }

    // Pentru findByCuloare (returnează Marca, Culoare)
    public List<MasinaMarcaCuloareInfo> mapMasinaListToMarcaCuloareInfoList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMarcaCuloareInfo)
                .toList();
    }

    private MasinaMarcaCuloareInfo mapMasinaToMarcaCuloareInfo(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return MasinaMarcaCuloareInfo.builder()
                .marca(nvl(e.getMarca()))
                .culoare(nvl(e.getCuloare()))
                .build();
    }

    // ******************************************************
    // Metode utilitare
    // ******************************************************

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }

    private static String nvl(String s) {
        return s == null ? "" : s;
    }
}