package mycode.masabiliardspring.mappers;

import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.model.Masina;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MasinaManualMapper {

    public Masina mapMasinaDtoToMasina(MasinaDto dto) {
        Objects.requireNonNull(dto, "dto is null");
        var e = new Masina();
        e.setMarca(trim(dto.marca()));
        e.setMarime(dto.marime());
        e.setCuloare(trim(dto.culoare()));
        return e;
    }

    public MasinaDto mapMasinaToMasinaDto(Masina e) {
        Objects.requireNonNull(e, "entity is null");
        return new MasinaDto(
                nvl(e.getMarca()),
                 e.getMarime(),
                nvl(e.getCuloare())
        );
    }

    //todo: maper de la masina la masinaCreateRequest

    public MasinaResponse mapMasinaToMasinaCreateResponse(Masina e){
        Objects.requireNonNull(e,"entity is null");
        return new MasinaResponse(
                e.getId(),
                nvl(e.getMarca()),
                e.getMarime(),
                nvl(e.getCuloare())
        );
    }

    public List<Masina> mapMasiniDtoListToMasinaList(List<MasinaDto> list) {
        if (list == null) return List.of();
        return list.stream().filter(Objects::nonNull).map(this::mapMasinaDtoToMasina).toList();
    }

    public List<MasinaDto> mapperMasinaListToMasinaDtoList(List<Masina> list) {
        if (list == null) return List.of();
        return list.stream().filter(Objects::nonNull).map(this::mapMasinaToMasinaDto).toList();
    }




    private static String trim(String s) { return s == null ? null : s.trim(); }
    private static String nvl(String s)   { return s == null ? "" : s; }
}