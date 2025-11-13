package sisepe.api.zona;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<DtoZona> buscarZonaPorNumero(@RequestParam int numZona) {
        return repositorio.findById(numZona).stream().map(DtoZona::new).toList();
    }

    @GetMapping("/municipio-sede")
    public List<DtoZona> buscarMunicipioSedeDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByMunicipioSedeDeUmaZona(numZona);
    }

    @GetMapping("/municipios")
    public List<DtoMunicipio> buscarMunicipiosDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByMunicipiosDeUmaZona(numZona);
    }

    @GetMapping("/secoes")
    public List<DtoZona> buscarSecoesDeUmaZona(@RequestParam int numZona) {
        log.info("Secao da zona encontrada: {}", numZona);
        return repositorio.findBySecoesdeUmaZona(numZona).stream().map(DtoZona::new).toList();
    }
}
