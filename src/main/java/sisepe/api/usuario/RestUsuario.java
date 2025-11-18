package sisepe.api.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sisepe.api.usuario.dto.DtoAtualizarUsuario;
import sisepe.api.usuario.dto.DtoCadUsuario;
import sisepe.api.usuario.dto.DtoListagemUsuarios;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/listar/todos/ativos")
    public ResponseEntity<List<DtoListagemUsuarios>> listaDeUsuarios() {
        var list = repositorio.findAllByAtivoTrue().stream().map(DtoListagemUsuarios::new).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/detalhes/{cpf}")
    public ResponseEntity<DtoListagemUsuarios> usuarioEspecifico(@PathVariable String cpf) {
        Usuario usuario = repositorio.getReferenceById(cpf);
        return ResponseEntity.ok(new DtoListagemUsuarios(usuario));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DtoListagemUsuarios> atualizarUsuario(@RequestBody @Valid DtoAtualizarUsuario dados) {
        var usuario = repositorio.getReferenceById(dados.cpf());
        usuario.atualizarDados(dados);
        return ResponseEntity.ok(new DtoListagemUsuarios(usuario));
    }

    @DeleteMapping("/excluir/{cpf}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable String cpf) {
        var usuario = repositorio.getReferenceById(cpf);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }
}