package sisepe.api.municipio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<DtoMunicipio> buscarMunicipiosPorCodTse(@RequestParam Integer codTse) {
        return repositorio.findById(codTse).stream().map(DtoMunicipio::new).toList();
    }

    @GetMapping("/zonas")
    public List<DtoZona> buscarZonasDeUmMunicipio(@RequestParam String nomeMunicipio) {
        return repositorio.findByZonasDeUmMunicipio(nomeMunicipio).stream().map(DtoZona::new).toList();
    }

    @GetMapping("/secoes")
    public List<DtoSecao> buscarSecoesDeUmMunicipio(@RequestParam String nomeMunicipio) {
        return repositorio.findBySecoesDeUmMunicipio(nomeMunicipio).stream().map(DtoSecao::new).toList();
    }
}
