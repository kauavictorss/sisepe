package sisepe.api.zona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.secao.Secao;

import java.util.List;

public interface RepoZona extends JpaRepository<Zona, Integer> {

    @Query("""
            select new sisepe.api.municipio.DtoMunicipio(m.codTse, m.nome, m.polo.numero, null, null)
            from Zona z
            join z.municipios m
            where z.numero = :numZona
            """)
    List<DtoMunicipio> findByCodTseDeUmaZona(int numZona);

    @Query("""
            select new sisepe.api.zona.DtoZona(z.numero, m.codTse, m.nome, null, null)
            from Zona z
            join z.municipios m
            where z.numero = :numZona
            """)
    List<DtoZona> findByMunicipioSedeDeUmaZona(int numZona);

    @Query("""
            select s
            from Secao s
            where s.zona.numero = :numZona
            """)
    List<Secao> findBySecoesdeUmaZona(int numZona);
}
