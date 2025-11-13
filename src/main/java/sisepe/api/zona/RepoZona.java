package sisepe.api.zona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.secao.Secao;

import java.util.List;

public interface RepoZona extends JpaRepository<Zona, Integer> {

    @Query(value = """
            SELECT new sisepe.api.municipio.DtoMunicipio(m.codTse, m.nome, m.polo.numero, null, null)
            FROM Municipio m
            JOIN m.zonas z
            WHERE z.numero = :numZona
            """)
    List<DtoMunicipio> findByMunicipiosDeUmaZona(int numZona);

    @Query("""
            SELECT new sisepe.api.zona.DtoZona(z.numero, m.codTse, m.nome, m.polo.numero, null)
            FROM Zona z
            JOIN z.municipioSede m
            WHERE z.numero = :numZona
            """)
    List<DtoZona> findByMunicipioSedeDeUmaZona(int numZona);

    @Query("""
            SELECT s
            FROM Secao s
            WHERE s.zona.numero = :numZona
            """)
    List<Secao> findBySecoesdeUmaZona(int numZona);
}
