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

    @GetMapping
    public List<DtoZona> numeroDaZona(@RequestParam int numZona) {
        return repositorio.findById(numZona).stream().map(DtoZona::new).toList();
    }

    @GetMapping("/cod-tse")
    public List<DtoMunicipio> codTseDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByCodTseDeUmaZona(numZona);
    }

    @GetMapping("/municipio-sede-zona")
    public List<DtoZona> municipioSedeDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByMunicipioSedeDeUmaZona(numZona);
    }

    @GetMapping("/secoes")
    public List<DtoZona> secoesDeUmaZona(@RequestParam int numZona) {
        log.info("Secao da zona encontrada: {}", numZona);
        return repositorio.findBySecoesdeUmaZona(numZona).stream().map(DtoZona::new).toList();
    }
}
