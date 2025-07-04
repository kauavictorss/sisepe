package sisepe.api.zona;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisepe.api.municipio.Municipio;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zonas")
@Getter
@NoArgsConstructor
public class Zona {
    @Id
    @Setter
    private int numero;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cod_municipio_sede")
    private Municipio municipioSede;

    @ManyToMany(mappedBy = "zonas")
    private List<Municipio> municipios;

    public void inserirMunicipio(Municipio municipio) {
        if (municipios == null) municipios = new ArrayList<>();
        if (!municipios.contains(municipio)) municipios.add(municipio);
    }

    public Zona(int numero) {
        this.numero = numero;
    }
}
