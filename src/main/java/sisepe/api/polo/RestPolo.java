package sisepe.api.polo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<DtoPolo>> buscarPoloPorNumero(@RequestParam int numPolo) {
        return ResponseEntity.ok(repositorio.findById(numPolo).stream().map(DtoPolo::new).toList());
    }

    @GetMapping("/municipios")
    public ResponseEntity<List<DtoMunicipio>> buscarMunicipiosDeUmPolo(@RequestParam int numPolo) {
        return ResponseEntity.ok(repositorio.findByMunicipiosDeUmPolo(numPolo).stream().map(DtoMunicipio::new).toList());
    }

    @GetMapping("/zonas")
    public ResponseEntity<List<DtoZona>> buscarZonasDeUmPolo(@RequestParam int numPolo) {
        return ResponseEntity.ok(repositorio.findAllZonasDeUmPolo(numPolo).stream().map(DtoZona::new).toList());
    }
}
