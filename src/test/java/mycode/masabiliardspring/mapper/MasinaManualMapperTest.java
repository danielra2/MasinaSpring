package mycode.masabiliardspring.mapper;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.dtos.MasinaIdMarcaCuloareInfo;
import mycode.masabiliardspring.dtos.MasinaMarcaCuloareInfo;
import mycode.masabiliardspring.dtos.MasinaResponse;
import mycode.masabiliardspring.mappers.MasinaManualMapper;
import mycode.masabiliardspring.model.Masina;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MasinaManualMapperTest {

    private final MasinaManualMapper mapper = new MasinaManualMapper();

    @Test
    void mapMasinaDtoToMasinaCopiesAndSanitizesValues() {
       // verifica daca trimul merge bine
        MasinaDto dto = new MasinaDto("  Dacia  ", 100, "  Alb  ");
        Masina result = mapper.mapMasinaDtoToMasina(dto);
        assertThat(result.getMarca()).isEqualTo("Dacia");
        assertThat(result.getMarime()).isEqualTo(100);
        assertThat(result.getCuloare()).isEqualTo("Alb");
    }

    @Test
    void mapMasinaListToResponseListFiltersNullElements() {
        // verifica daca null merge bine
        Masina valid = new Masina(1L, "Audi", 200, "Negru");

        List<MasinaResponse> resultList = mapper.mapMasinaListToResponseList(Arrays.asList(valid, null));

        assertThat(resultList).hasSize(1).first().extracting(MasinaResponse::id, MasinaResponse::marca).containsExactly(1L, "Audi");
    }

    @Test
    void mapMasinaListToMarcaCuloareInfoListHandlesNullSource() {
        assertThat(mapper.mapMasinaListToMarcaCuloareInfoList(null)).isEmpty();
        Masina m = new Masina(2L, "BMW", 150, "Gri");
        List<MasinaMarcaCuloareInfo> result = mapper.mapMasinaListToMarcaCuloareInfoList(Arrays.asList(m));
        assertThat(result).hasSize(1).first().extracting(MasinaMarcaCuloareInfo::marca, MasinaMarcaCuloareInfo::culoare).containsExactly("BMW", "Gri");
    }

    @Test
    //todo: testez nvl
    void mapMasinaToMasinaResponseHandlesNullFields() {
        Masina e = new Masina();
        e.setId(10L);
        e.setMarca(null);
        e.setCuloare(null);
        MasinaResponse response = mapper.mapMasinaToMasinaResponse(e);
        assertThat(response.marca()).isEmpty();
        assertThat(response.culoare()).isEmpty();
        assertThat(response.id()).isEqualTo(10L);
    }
}