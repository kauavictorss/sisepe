package sisepe.api.secao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("secoes")
public class RestSecao {
    private final RepoSecao repositorio;

    @GetMapping
    public ResponseEntity<List<DtoSecao>> consultarTodasSecoes() {
        log.info("Consultando todas as seções");
        return ResponseEntity.ok(repositorio.findAll().stream().map(DtoSecao::new).toList());
    }

    @GetMapping("/zona/{numZona}")
    public ResponseEntity<List<DtoSecao>> listarSecoesDeUmaZona(@PathVariable Integer numZona) {
        log.info("Consultando lista de seções da Zona: {}", numZona);
        return ResponseEntity.ok(repositorio.findByZonaNumero(numZona).stream().map(DtoSecao::new).toList());
    }
}
