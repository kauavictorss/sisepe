package sisepe.api.secao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sisepe.api.municipio.Municipio;
import sisepe.api.polo.Polo;
import sisepe.api.zona.Zona;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestSecao.class)
class RestSecaoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepoSecao repoSecao;

    @Test
    @DisplayName("Deve consultar todas as seções")
    void deveConsultarTodasSecoes() throws Exception {
        Secao secao = new Secao(15);
        secao.setId(200);
        
        Municipio municipio = new Municipio(100, "RECIFE");
        secao.setMunicipio(municipio);
        
        Zona zona = new Zona(1);
        secao.setZona(zona);
        
        Polo polo = new Polo(10);
        secao.setPolo(polo);

        when(repoSecao.findAll()).thenReturn(List.of(secao));

        mockMvc.perform(get("/secoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(200))
                .andExpect(jsonPath("$[0].numSecao").value(15))
                .andExpect(jsonPath("$[0].numZona").value(1))
                .andExpect(jsonPath("$[0].numPolo").value(10))
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"));
    }

    @Test
    @DisplayName("Deve listar seções de uma zona específica")
    void deveListarSecoesDeUmaZona() throws Exception {
        int numZona = 1;
        Secao secao = new Secao(15);
        secao.setId(200);
        
        Municipio municipio = new Municipio(100, "RECIFE");
        secao.setMunicipio(municipio);
        
        Zona zona = new Zona(numZona);
        secao.setZona(zona);
        
        Polo polo = new Polo(10);
        secao.setPolo(polo);

        when(repoSecao.findByZonaNumero(numZona)).thenReturn(List.of(secao));

        mockMvc.perform(get("/secoes/zona/{numZona}", numZona))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(200))
                .andExpect(jsonPath("$[0].numSecao").value(15))
                .andExpect(jsonPath("$[0].numZona").value(numZona))
                .andExpect(jsonPath("$[0].numPolo").value(10))
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"));
    }
}
