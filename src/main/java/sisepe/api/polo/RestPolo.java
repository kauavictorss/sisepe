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

    @GetMapping("/numero")
    public List<DtoPolo> buscarPoloPorNumero(@RequestParam int numPolo) {
        return repositorio.findById(numPolo).stream().map(DtoPolo::new).toList();
    }

    @GetMapping("/municipios")
    public List<DtoMunicipio> buscarMunicipiosDeUmPolo(@RequestParam int numPolo) {
        return repositorio.findByMunicipiosDeUmPolo(numPolo);
    }

    @GetMapping("/zonas")
    public List<DtoZona> buscarZonasDeUmPolo(@RequestParam int numPolo) {
        return repositorio.findAllZonasDeUmPolo(numPolo).stream().map(DtoZona::new).toList();
    }
}
