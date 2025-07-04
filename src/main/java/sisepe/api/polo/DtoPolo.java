package sisepe.api.polo;

public record DtoPolo(int numPolo, int munSede, String nomeMunicipio, int zonas) {
    public DtoPolo(Polo polo) {
        this(
                polo.getNumero(),
                polo.getMunicipioSede().getCodTse(),
                polo.getMunicipioSede().getNome(),
                polo.getMunicipios().getFirst().getZonas().getFirst().getNumero()
        );
    }
}
