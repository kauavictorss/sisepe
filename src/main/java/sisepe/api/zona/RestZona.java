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
    public List<DtoZona> zonaPorNumero(@RequestParam int numZona) {
        return repositorio.findById(numZona).stream().map(DtoZona::new).toList();
    }

    @GetMapping("/municipio-sede-zona")
    public List<DtoMunicipio> municipioSedeDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByMunicipioSedeDaZona(numZona);
    }

    @GetMapping("/municipio-zona")
    public List<DtoZona> municipiosDeUmaZona(@RequestParam int numZona) {
        return repositorio.findByNomeDoMunicipiosDeUmaZona(numZona);
    }

    @GetMapping("/secao-zona")
    public List<DtoZona> secoesDeUmaZona(@RequestParam int numZona) {
        log.info("Secao da zona encontrada: {}", numZona);
        return repositorio.findBySecoesdeUmaZona(numZona).stream().map(DtoZona::new).toList();
    }
}
