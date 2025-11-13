package sisepe.api.secao;

public record DtoSecao(int id, int numSecao, int numZona, int numPolo, int codTse, String nomeMunicipio) {

    public DtoSecao(Secao secao) {
        this (
                secao.getId(),
                secao.getNumero(),
                secao.getZona().getNumero(),
                secao.getPolo().getNumero(),
                secao.getMunicipio().getCodTse(),
                secao.getMunicipio().getNome()
        );
    }
}
