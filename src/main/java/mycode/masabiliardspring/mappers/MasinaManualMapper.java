package mycode.masabiliardspring.mappers;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaIdMarcaCuloareInfo;
import mycode.masabiliardspring.dtos.MasinaMarcaCuloareInfo;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.model.Masina;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MasinaManualMapper {

    public Masina mapMasinaDtoToMasina(MasinaDto dto) {
        Objects.requireNonNull(dto, "dto is null");
        var e = new Masina();
        // Folosim sintaxa record: dto.marca()
        e.setMarca(trim(dto.marca()));
        e.setMarime(dto.marime());
        e.setCuloare(trim(dto.culoare()));
        return e;
    }

    public MasinaResponse mapMasinaToMasinaResponse(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return new MasinaResponse(
                e.getId(),
                nvl(e.getMarca()),
                e.getMarime(),
                nvl(e.getCuloare())
        );
    }

    public List<MasinaResponse> mapMasinaListToResponseList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMasinaResponse)
                .toList();
    }

    public List<MasinaIdMarcaCuloareInfo> mapMasinaListToIdMarcaCuloareInfoList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToIdMarcaCuloareInfo)
                .toList();
    }

    private MasinaIdMarcaCuloareInfo mapMasinaToIdMarcaCuloareInfo(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return new MasinaIdMarcaCuloareInfo(
                e.getId(),
                nvl(e.getMarca()),
                nvl(e.getCuloare())
        );
    }

    public List<MasinaMarcaCuloareInfo> mapMasinaListToMarcaCuloareInfoList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMarcaCuloareInfo)
                .toList();
    }

    private MasinaMarcaCuloareInfo mapMasinaToMarcaCuloareInfo(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return new MasinaMarcaCuloareInfo(
                nvl(e.getMarca()),
                nvl(e.getCuloare())
        );
    }

    private static String trim(String s) { return s == null ? null : s.trim(); }
    private static String nvl(String s)   { return s == null ? "" : s; }
}