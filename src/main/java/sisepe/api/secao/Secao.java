package sisepe.api.secao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisepe.api.municipio.Municipio;
import sisepe.api.polo.Polo;
import sisepe.api.zona.Zona;

@Entity
@Table(name = "secoes")
@Getter
@Setter
@NoArgsConstructor
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "num_zona")
    private Zona zona;

    @ManyToOne
    @JoinColumn(name = "cod_tse")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "num_polo")
    private Polo polo;

    public Secao(int numSecao) {
        this.numero = numSecao;
    }
}
