package sisepe.api.polo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisepe.api.municipio.Municipio;

import java.util.List;

@Entity
@Table(name = "polos")
@Getter
@Setter
@NoArgsConstructor
public class Polo {
    @Id
    private int numero;

    @ManyToOne
    @JoinColumn(name = "cod_municipio_sede")
    private Municipio municipioSede;

    @OneToMany(mappedBy = "polo")
    private List<Municipio> municipios;

    public Polo(int numPolo) {
        this.numero = numPolo;
    }

    public void inserirMunicipio(Municipio municipio) {
        if (municipios == null) municipios = new java.util.ArrayList<>();
        if (!municipios.contains(municipio)) {
            municipios.add(municipio);
            municipio.setPolo(this);
        }
    }
}
