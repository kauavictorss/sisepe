package sisepe.api.municipio;

import com.fasterxml.jackson.annotation.JsonInclude;
import sisepe.api.secao.Secao;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoMunicipio(Integer codTse, String nome, Integer numPolo, Integer numZona, Integer numSecao) {

    public DtoMunicipio(Municipio municipio) {
        this(
                municipio.getCodTse(),
                municipio.getNome(),
                municipio.getPolo().getNumero(),
                municipio.getZonas().getFirst().getNumero(),
                null
        );
    }

    public DtoMunicipio(Secao secao) {
        this(
                null,
                null,
                null,
                null,
                secao.getNumero()
        );
    }
}
