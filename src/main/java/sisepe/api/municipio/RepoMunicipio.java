package sisepe.api.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.secao.Secao;
import sisepe.api.zona.Zona;

import java.util.List;

public interface RepoMunicipio extends JpaRepository<Municipio, Integer> {

    @Query("""
            SELECT z
            FROM Zona z
            WHERE z.municipioSede.nome = :nomeMunicipio
            """)
    List<Zona> findByZonasDeUmMunicipio(String nomeMunicipio);

    @Query("""
            SELECT s
            FROM Secao s
            WHERE s.municipio.nome = :nome
            """)
    List<Secao> findBySecoesDeUmMunicipio(String nome);
}
