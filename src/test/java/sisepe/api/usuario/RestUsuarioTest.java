package sisepe.api.usuario;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sisepe.api.usuario.dto.DtoCadUsuario;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestUsuarioTest {
    @Mock
    private RepoUsuario repositorio;

    @Test
    void cadastrarUsuario() {
        Usuario usuario = new Usuario(new DtoCadUsuario(
                "09145612309",
                "Usuário Teste",
                "teste.user@email.com"
        ));
        assertNotNull(usuario);
    }

    @Test
    void atualizarUsuario() {

    }
}