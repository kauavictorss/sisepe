package sisepe.api.municipio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sisepe.api.polo.Polo;
import sisepe.api.secao.Secao;
import sisepe.api.zona.Zona;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestMunicipio.class)
class RestMunicipioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepoMunicipio repoMunicipio;

    @Test
    @DisplayName("Deve buscar município por código TSE")
    void deveBuscarMunicipioPorCodTse() throws Exception {
        int codTse = 100;
        Municipio municipio = new Municipio(codTse, "RECIFE");
        Polo polo = new Polo(10);
        municipio.setPolo(polo);
        
        Zona zona = new Zona(1);
        municipio.inserirZona(zona);

        when(repoMunicipio.findById(codTse)).thenReturn(Optional.of(municipio));

        mockMvc.perform(get("/municipios/codTse").param("codTse", String.valueOf(codTse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codTse").value(codTse))
                .andExpect(jsonPath("$[0].nome").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10))
                .andExpect(jsonPath("$[0].numZona").value(1));
    }

    @Test
    @DisplayName("Deve buscar zonas de um município")
    void deveBuscarZonasDeUmMunicipio() throws Exception {
        String nome = "RECIFE";
        Zona zona = new Zona(1);
        Municipio municipioSede = new Municipio(100, "RECIFE");
        Polo polo = new Polo(10);
        municipioSede.setPolo(polo);
        zona.setMunicipioSede(municipioSede);

        when(repoMunicipio.findByZonasDeUmMunicipio(nome)).thenReturn(List.of(zona));

        mockMvc.perform(get("/municipios/zonas").param("nomeMunicipio", nome))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numZona").value(1))
                .andExpect(jsonPath("$[0].municipioCodTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10));
    }

    @Test
    @DisplayName("Deve buscar municípios do mesmo polo")
    void deveBuscarPoloDeUmMunicipio() throws Exception {
        String nome = "RECIFE";
        Municipio recife = new Municipio(100, "RECIFE");
        Polo polo = new Polo(10);
        recife.setPolo(polo);
        
        Zona zona = new Zona(1);
        recife.inserirZona(zona);

        when(repoMunicipio.findByPoloDeUmMunicipio(nome)).thenReturn(List.of(recife));

        mockMvc.perform(get("/municipios/polos").param("nomeMunicipio", nome))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nome").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(10))
                .andExpect(jsonPath("$[0].numZona").value(1));
    }

    @Test
    @DisplayName("Deve buscar seções de um município")
    void deveBuscarSecoesDeUmMunicipio() throws Exception {
        String nome = "RECIFE";
        Municipio municipio = new Municipio(100, nome);
        Zona zona = new Zona(1);
        Polo polo = new Polo(10);
        
        Secao secao = new Secao(15);
        secao.setId(200);
        secao.setMunicipio(municipio);
        secao.setZona(zona);
        secao.setPolo(polo);

        when(repoMunicipio.findBySecoesDeUmMunicipio(nome)).thenReturn(List.of(secao));

        mockMvc.perform(get("/municipios/secoes").param("nomeMunicipio", nome))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(200))
                .andExpect(jsonPath("$[0].numSecao").value(15))
                .andExpect(jsonPath("$[0].numZona").value(1))
                .andExpect(jsonPath("$[0].numPolo").value(10))
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"));
    }
}
