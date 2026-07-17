package sisepe.api.polo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sisepe.api.municipio.Municipio;
import sisepe.api.zona.Zona;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestPolo.class)
class RestPoloTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepoPolo repoPolo;

    @Test
    @DisplayName("Deve buscar polo por número")
    void deveBuscarPoloPorNumero() throws Exception {
        int numPolo = 10;
        Polo polo = new Polo(numPolo);
        
        Municipio municipioSede = new Municipio(100, "RECIFE");
        polo.setMunicipioSede(municipioSede);
        
        Municipio mun1 = new Municipio(200, "CARUARU");
        Zona zona = new Zona(1);
        mun1.inserirZona(zona);
        
        polo.inserirMunicipio(mun1);

        when(repoPolo.findById(numPolo)).thenReturn(Optional.of(polo));

        mockMvc.perform(get("/polos/numero").param("numPolo", String.valueOf(numPolo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numPolo").value(numPolo))
                .andExpect(jsonPath("$[0].munSede").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"))
                .andExpect(jsonPath("$[0].zonas").value(1));
    }

    @Test
    @DisplayName("Deve buscar municípios de um polo")
    void deveBuscarMunicipiosDeUmPolo() throws Exception {
        int numPolo = 10;
        Polo polo = new Polo(numPolo);
        Municipio municipio = new Municipio(100, "RECIFE");
        municipio.setPolo(polo);
        
        Zona zona = new Zona(1);
        municipio.inserirZona(zona);

        when(repoPolo.findByMunicipiosDeUmPolo(numPolo)).thenReturn(List.of(municipio));

        mockMvc.perform(get("/polos/municipios").param("numPolo", String.valueOf(numPolo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codTse").value(100))
                .andExpect(jsonPath("$[0].nome").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(numPolo))
                .andExpect(jsonPath("$[0].numZona").value(1));
    }

    @Test
    @DisplayName("Deve buscar zonas de um polo")
    void deveBuscarZonasDeUmPolo() throws Exception {
        int numPolo = 10;
        Zona zona = new Zona(1);
        
        Municipio municipioSede = new Municipio(100, "RECIFE");
        Polo polo = new Polo(numPolo);
        municipioSede.setPolo(polo);
        zona.setMunicipioSede(municipioSede);

        when(repoPolo.findAllZonasDeUmPolo(numPolo)).thenReturn(List.of(zona));

        mockMvc.perform(get("/polos/zonas").param("numPolo", String.valueOf(numPolo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numZona").value(1))
                .andExpect(jsonPath("$[0].municipioCodTse").value(100))
                .andExpect(jsonPath("$[0].nomeMunicipio").value("RECIFE"))
                .andExpect(jsonPath("$[0].numPolo").value(numPolo));
    }
}
