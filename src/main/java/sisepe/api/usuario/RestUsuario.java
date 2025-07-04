package sisepe.api.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sisepe.api.usuario.dto.DtoAtualizarUsuario;
import sisepe.api.usuario.dto.DtoCadUsuario;
import sisepe.api.usuario.dto.DtoListagemUsuarios;

import java.net.URI;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class RestUsuario {
    private final RepoUsuario repositorio;

    @PostMapping("/cad")
    @Transactional
    public ResponseEntity<DtoListagemUsuarios> cadastrarUsuario(@RequestBody @Valid DtoCadUsuario dtoCadUsuario, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(dtoCadUsuario);
        repositorio.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{cpf}").buildAndExpand(usuario.getCpf()).toUri();
        log.info("Usuário cadastrado com sucesso: {}", dtoCadUsuario.nome());
        return ResponseEntity.created(uri).body(new DtoListagemUsuarios(usuario));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DtoListagemUsuarios>> listaDeUsuarios(@PageableDefault(size = 12, sort = {"nome"}) Pageable paginacao) {
        var page = repositorio.findAll(paginacao).map(DtoListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhes/{cpf}")
    public ResponseEntity<DtoListagemUsuarios> usuarioEspecifico(@PathVariable String cpf) {
        Usuario usuario = repositorio.getReferenceById(cpf);
        return ResponseEntity.ok(new DtoListagemUsuarios(usuario));
    }

    @PutMapping("/atualizar")
    @Transactional
    public void atualizarUsuario(@RequestBody @Valid DtoAtualizarUsuario dados) {
        var usuario = repositorio.getReferenceById(dados.cpf());
        usuario.atualizarDados(dados);
    }

//    @DeleteMapping("/excluir/{cpf}")
//    @Transactional
//    public void excluirUsuario(@PathVariable String cpf) {
//        var usuario = repository.getReferenceById(cpf);
//        usuario.excluir();
//    }
}
