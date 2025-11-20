package sisepe.api.zona;

import com.fasterxml.jackson.annotation.JsonInclude;
import sisepe.api.secao.Secao;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoZona(Integer numZona, Integer municipioCodTse, String nomeMunicipio, Integer numPolo, Integer numSecao) {

    public DtoZona(Zona zona) {
        this(
                zona.getNumero(),
                zona.getMunicipioSede().getCodTse(),
                zona.getMunicipioSede().getNome(),
                zona.getMunicipioSede().getPolo().getNumero(),
                null
        );
    }

    public DtoZona(Secao secao) {
        this(
                null,
                secao.getMunicipio().getCodTse(),
                secao.getMunicipio().getNome(),
                null,
                secao.getNumero()
        );
    }
}
