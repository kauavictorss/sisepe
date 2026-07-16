package sisepe.api.zona;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.municipio.Municipio;
import sisepe.api.polo.Polo;
import sisepe.api.secao.Secao;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestZona.class)
class RestZonaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepoZona repoZona;

    @Test
    @DisplayName("Deve buscar zona por número e retornar DtoZona")
    void deveBuscarZonaPorNumero() throws Exception {
        int numZona = 1;
        Zona zona = new Zona(numZona);
        Municipio municipioSede = new Municipio(100, "RECIFE");
        Polo polo = new Polo(10);
        municipioSede.setPolo(polo);
        zona.setMunicipioSede(municipioSede);

        when(repoZona.findById(numZona)).thenReturn(Optional.of(zona));

        mockMvc.perform(get("/zonas/numero").param("numZona", String.valueOf(numZona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numZona").value(numZona))
                .andExpect(jsonPath("$[0].municipioCodTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando buscar zona por número inexistente")
    void deveRetornarVazioQuandoZonaNaoExiste() throws Exception {
        int numZona = 999;
        when(repoZona.findById(numZona)).thenReturn(Optional.empty());

        mockMvc.perform(get("/zonas/numero").param("numZona", String.valueOf(numZona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("Deve buscar município sede de uma zona")
    void deveBuscarMunicipioSedeDeUmaZona() throws Exception {
        int numZona = 1;
        DtoZona dto = new DtoZona(1, 100, "RECIFE", 10, null);
        when(repoZona.findByMunicipioSedeDeUmaZona(numZona)).thenReturn(List.of(dto));

        mockMvc.perform(get("/zonas/municipio-sede").param("numZona", String.valueOf(numZona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numZona").value(1))
                .andExpect(jsonPath("$[0].municipioCodTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10));
    }

    @Test
    @DisplayName("Deve buscar municípios vinculados a uma zona")
    void deveBuscarMunicipiosDeUmaZona() throws Exception {
        int numZona = 1;
        DtoMunicipio dto = new DtoMunicipio(100, "RECIFE", 10, null, null);
        when(repoZona.findByMunicipiosDeUmaZona(numZona)).thenReturn(List.of(dto));

        mockMvc.perform(get("/zonas/municipios").param("numZona", String.valueOf(numZona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nome").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10));
    }

    @Test
    @DisplayName("Deve buscar seções vinculadas a uma zona")
    void deveBuscarSecoesDeUmaZona() throws Exception {
        int numZona = 1;
        Municipio municipio = new Municipio(100, "RECIFE");
        Secao secao = new Secao(15);
        secao.setMunicipio(municipio);

        when(repoZona.findBySecoesdeUmaZona(numZona)).thenReturn(List.of(secao));

        mockMvc.perform(get("/zonas/secoes").param("numZona", String.valueOf(numZona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numSecao").value(15))
                .andExpect(jsonPath("$[0].municipioCodTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"));
    }
}
