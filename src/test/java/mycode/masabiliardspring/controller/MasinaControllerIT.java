package mycode.masabiliardspring.controller;


import com.jayway.jsonpath.JsonPath;
import mycode.masabiliardspring.dtos.MasinaResponseListRequest;
import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MasinaControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MasinaRepository repository;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    void createMasinaEndpointPersistsEntity()throws Exception{
        String payload="{"+
                "\"marca\":\"Audi\","+
                "\"marime\":3,"+
                "\"culoare\":\"Alb\""+
                "}";
        mockMvc.perform(post("/api/masini/add").contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(status().isCreated()).andExpect(jsonPath("$.marca").value("Audi"));
        assertThat(repository.findByMarcaAndCuloare("Audi","Alb").isPresent());

    }

    @Test
    void getAllMasiniReturnsResults()throws Exception{


        Masina m1=new Masina(null,"Audi",6,"Alb");
        Masina m2=new Masina(null,"Bmw",5,"Negru");
        repository.saveAll(List.of(m1,m2));

        mockMvc.perform(get("/api/masini")).andExpect(status().isOk())
                .andExpect(jsonPath("$.masiniList.length()").value(2));
    }

    private Masina masina(String marca,int marime,String culoare){
        Masina entity=new Masina();
        entity.setMarca(marca);
        entity.setMarime(marime);
        entity.setCuloare(culoare);
        return entity;
    }

    @Test
    void invalidSortParameterShouldReturnBadRequest() throws Exception {
        repository.save(new Masina(null, "Audi", 10, "Alb"));

        mockMvc.perform(get("/api/masini").param("sort", "campInexistent,asc")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").value("Parametrii furnizati sunt invalizi"));
    }

}
