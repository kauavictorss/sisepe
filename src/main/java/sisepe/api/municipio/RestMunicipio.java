package sisepe.api.municipio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisepe.api.secao.DtoSecao;
import sisepe.api.zona.DtoZona;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("municipios")
public class RestMunicipio {
    private final RepoMunicipio repositorio;

    @GetMapping("/codTse")
    public ResponseEntity<List<DtoMunicipio>> buscarMunicipiosPorCodTse(@RequestParam Integer codTse) {
        return ResponseEntity.ok(repositorio.findById(codTse).stream().map(DtoMunicipio::new).toList());
    }

    @GetMapping("/zonas")
    public ResponseEntity<List<DtoZona>> buscarZonasDeUmMunicipio(@RequestParam String nomeMunicipio) {
        return ResponseEntity.ok(repositorio.findByZonasDeUmMunicipio(nomeMunicipio).stream().map(DtoZona::new).toList());
    }

    @GetMapping("/secoes")
    public ResponseEntity<List<DtoSecao>> buscarSecoesDeUmMunicipio(@RequestParam String nomeMunicipio) {
        return ResponseEntity.ok(repositorio.findBySecoesDeUmMunicipio(nomeMunicipio).stream().map(DtoSecao::new).toList());
    }
}
