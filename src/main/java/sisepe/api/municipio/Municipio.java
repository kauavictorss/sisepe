package sisepe.api.municipio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisepe.api.polo.Polo;
import sisepe.api.zona.Zona;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "municipios")
@Getter
@NoArgsConstructor
public class Municipio {
    @Id
    @Setter
    private int codTse;

    @Setter
    private String nome;

    @ManyToOne
    @JoinColumn(name = "num_polo")
    @Setter
    private Polo polo;

    @ManyToMany
    @JoinTable(
            name = "municipio_zona",
            joinColumns = @JoinColumn(name = "cod_tse"),
            inverseJoinColumns = @JoinColumn(name = "num_zona"))
    private List<Zona> zonas;

    public Municipio(int codTse, String nomeMunicipio) {
        this.codTse = codTse;
        this.nome = nomeMunicipio;
    }

    public void inserirZona(Zona zona) {
        if (zonas == null) zonas = new ArrayList<>();
        if (!zonas.contains(zona)) zonas.add(zona);
    }
}
