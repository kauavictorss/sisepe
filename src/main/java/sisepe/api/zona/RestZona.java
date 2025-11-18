package sisepe.api.zona;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisepe.api.municipio.DtoMunicipio;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("zonas")
public class RestZona {
    private final RepoZona repositorio;

    @GetMapping("/numero")
    public ResponseEntity<List<DtoZona>> buscarZonaPorNumero(@RequestParam int numZona) {
        return ResponseEntity.ok(repositorio.findById(numZona).stream().map(DtoZona::new).toList());
    }

    @GetMapping("/municipio-sede")
    public ResponseEntity<List<DtoZona>> buscarMunicipioSedeDeUmaZona(@RequestParam int numZona) {
        return ResponseEntity.ok(repositorio.findByMunicipioSedeDeUmaZona(numZona));
    }

    @GetMapping("/municipios")
    public ResponseEntity<List<DtoMunicipio>> buscarMunicipiosDeUmaZona(@RequestParam int numZona) {
        return ResponseEntity.ok(repositorio.findByMunicipiosDeUmaZona(numZona));
    }

    @GetMapping("/secoes")
    public ResponseEntity<List<DtoZona>> buscarSecoesDeUmaZona(@RequestParam int numZona) {
        log.info("Secao da zona encontrada: {}", numZona);
        return ResponseEntity.ok(repositorio.findBySecoesdeUmaZona(numZona).stream().map(DtoZona::new).toList());
    }
}
