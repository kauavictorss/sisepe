package sisepe.api.polo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sisepe.api.municipio.Municipio;
import sisepe.api.zona.Zona;

import java.util.List;

public interface RepoPolo extends JpaRepository<Polo, Integer> {

    @Query("""
            SELECT m
            FROM Municipio m
            WHERE m.polo.numero = :numPolo
            """)
    List<Municipio> findByMunicipiosDeUmPolo(int numPolo);

    @Query("""
            SELECT distinct z
            FROM Polo p
            JOIN p.municipios m
            JOIN m.zonas z
            WHERE p.numero = :numPolo
            """)
    List<Zona> findAllZonasDeUmPolo(int numPolo);
}
