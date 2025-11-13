package sisepe.api.polo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.zona.Zona;

import java.util.List;

public interface RepoPolo extends JpaRepository<Polo, Integer> {

    @Query("""
            SELECT new sisepe.api.municipio.DtoMunicipio(m.codTse, m.nome, null, null, null)
            FROM Polo p
            JOIN p.municipios m
            JOIN m.zonas z
            WHERE p.numero = :numPolo
            """)
    List<DtoMunicipio> findByMunicipiosDeUmPolo(int numPolo);

    @Query("""
            SELECT p
            FROM Polo p
            WHERE p.numero = :numPolo
            """)
    List<Polo> findByNumeroPoloDeUmaZona(int numPolo);

    @Query("""
            SELECT distinct z
            FROM Polo p
            JOIN p.municipios m
            JOIN m.zonas z
            WHERE p.numero = :numPolo
            """)
    List<Zona> findAllZonasDeUmPolo(int numPolo);
}
