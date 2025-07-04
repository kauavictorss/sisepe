package sisepe.api.municipio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sisepe.api.zona.DtoZona;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("municipio")
public class RestMunicipio {
    private final RepoMunicipio repositorio;

    @GetMapping("/{codTse}")
    public List<DtoMunicipio> municipioPorCodTse(@PathVariable Integer codTse) {
        return repositorio.findById(codTse).stream().map(DtoMunicipio::new).toList();
    }

    @GetMapping("/zona-municipio")
    public List<DtoZona> zonasDeUmMunicipio(@RequestParam String nomeMunicipio) {
        return repositorio.findByZonasDeUmMunicipio(nomeMunicipio).stream().map(DtoZona::new).toList();
    }

    @PostMapping("/secao-municipio/{nome}")
    public List<DtoMunicipio> secoesDeUmMunicipioApi(@PathVariable String nome) {
        return repositorio.findBySecoesDeUmMunicipio(nome).stream().map(DtoMunicipio::new).toList();
    }

    @GetMapping("/secao-municipio")
    public List<DtoMunicipio> secoesDeUmMunicipioWeb(@RequestParam String nome) {
        return repositorio.findBySecoesDeUmMunicipio(nome).stream().map(DtoMunicipio::new).toList();
    }
}
