package sisepe.api.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.secao.Secao;
import sisepe.api.zona.Zona;

import java.util.List;

public interface RepoMunicipio extends JpaRepository<Municipio, Integer> {

    @Query("""
            select z
            from Zona z
            where z.municipioSede.nome = :nomeMunicipio
            """)
    List<Zona> findByZonasDeUmMunicipio(String nomeMunicipio);

    @Query("""
            select s
            from Secao s
            where s.municipio.nome = :nome
            """)
    List<Secao> findBySecoesDeUmMunicipio(String nome);
}
