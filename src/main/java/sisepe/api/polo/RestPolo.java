package sisepe.api.polo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sisepe.api.municipio.DtoMunicipio;
import sisepe.api.zona.DtoZona;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("polos")
public class RestPolo {
    private final RepoPolo repositorio;

    @GetMapping("/municipios-do-polo")
    public List<DtoMunicipio> municipiosDeUmPolo(@RequestParam int numPolo) {
        return repositorio.findByMunicipiosDeUmPolo(numPolo);
    }

    @GetMapping("/zonas-do-polo")
    public List<DtoZona> zonasDeUmPolo(@RequestParam int numPolo) {
        return repositorio.findAllZonasDeUmPolo(numPolo).stream().map(zona -> new DtoZona(
                zona.getNumero(),
                zona.getMunicipioSede().getCodTse(),
                zona.getMunicipioSede().getNome(),
                null,
                null
        )).toList();
    }

    @GetMapping("/municipio")
    public List<DtoPolo> numPoloDeUmMunicipio(@RequestParam int numPolo) {
        return repositorio.findById(numPolo).stream().map(DtoPolo::new).toList();
    }

    @GetMapping("/polo-da-zona")
    public List<DtoPolo> numPoloDeUmaZona(@RequestParam int numPolo) {
        return repositorio.findByNumeroPoloDeUmaZona(numPolo).stream().map(DtoPolo::new).toList();
    }
}
