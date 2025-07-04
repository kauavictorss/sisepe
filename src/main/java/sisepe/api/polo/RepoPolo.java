package sisepe.api.polo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.zona.Zona;

import java.util.List;

public interface RepoPolo extends JpaRepository<Polo, Integer> {

    @Query("""
            select new sisepe.api.municipio.DtoMunicipio(m.codTse, m.nome, null, null, null)
            from Polo p
            join p.municipios m
            join m.zonas z
            where p.numero = :numPolo
            """)
    List<DtoMunicipio> findByMunicipiosDeUmPolo(int numPolo);

    @Query("""
            select p
            from Polo p
            where p.numero = :numPolo
            """)
    List<Polo> findByNumeroPoloDeUmaZona(int numPolo);

    @Query("""
            select distinct z
            from Polo p
            join p.municipios m
            join m.zonas z
            where p.numero = :numPolo
            """)
    List<Zona> findAllZonasDeUmPolo(int numPolo);
}
