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

    @Query("""
            SELECT m
            FROM Municipio m
            WHERE m.polo = (SELECT m2.polo FROM Municipio m2 WHERE m2.nome = :nomeMunicipio)
            """)
    List<Municipio> findByPoloDeUmMunicipio(String nomeMunicipio);
}
