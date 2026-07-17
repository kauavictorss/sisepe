package sisepe.api.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sisepe.api.usuario.dto.DtoAtualizarUsuario;
import sisepe.api.usuario.dto.DtoCadUsuario;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestUsuario.class)
class RestUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RepoUsuario repoUsuario;

    @Test
    @DisplayName("Deve cadastrar usuário com sucesso quando os dados são válidos")
    void deveCadastrarUsuarioComDadosValidos() throws Exception {
        DtoCadUsuario dto = new DtoCadUsuario("12345678901", "Usuario Teste", "teste@email.com");
        
        mockMvc.perform(post("/usuarios/cad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("12345678901"))
                .andExpect(jsonPath("$.nome").value("Usuario Teste"))
                .andExpect(jsonPath("$.email").value("teste@email.com"));

        verify(repoUsuario).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve retornar status 400 (Bad Request) quando tentar cadastrar usuário com dados inválidos")
    void deveRetornarBadRequestQuandoDadosInvalidos() throws Exception {
        DtoCadUsuario dto = new DtoCadUsuario("12345", "", "email_invalido"); // CPF curto, sem nome, email sem @

        mockMvc.perform(post("/usuarios/cad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve listar todos os usuários ativos")
    void deveListarTodosUsuariosAtivos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678901");
        usuario.setNome("Usuario Ativo");
        usuario.setEmail("ativo@email.com");
        usuario.setAtivo(true);

        when(repoUsuario.findAllByAtivoTrue()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios/listar/todos/ativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf").value("12345678901"))
                .andExpect(jsonPath("$[0].nome").value("Usuario Ativo"))
                .andExpect(jsonPath("$[0].email").value("ativo@email.com"));
    }

    @Test
    @DisplayName("Deve retornar detalhes do usuário específico pelo CPF")
    void deveRetornarDetalhesUsuarioPorCpf() throws Exception {
        String cpf = "12345678901";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNome("Usuario Teste");
        usuario.setEmail("teste@email.com");

        when(repoUsuario.getReferenceById(cpf)).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/detalhes/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(cpf))
                .andExpect(jsonPath("$.nome").value("Usuario Teste"))
                .andExpect(jsonPath("$.email").value("teste@email.com"));
    }

    @Test
    @DisplayName("Deve atualizar dados do usuário")
    void deveAtualizarUsuario() throws Exception {
        String cpf = "12345678901";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNome("Nome Original");
        usuario.setEmail("original@email.com");

        DtoAtualizarUsuario updateDto = new DtoAtualizarUsuario("Nome Atualizado", "atualizado@email.com", cpf);

        when(repoUsuario.getReferenceById(cpf)).thenReturn(usuario);

        mockMvc.perform(put("/usuarios/atualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(cpf))
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"))
                .andExpect(jsonPath("$.email").value("atualizado@email.com"));
    }

    @Test
    @DisplayName("Deve realizar exclusão lógica do usuário pelo CPF")
    void deveExcluirUsuarioLogicamente() throws Exception {
        String cpf = "12345678901";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNome("Usuario Excluir");
        usuario.setEmail("excluir@email.com");
        usuario.setAtivo(true);

        when(repoUsuario.getReferenceById(cpf)).thenReturn(usuario);

        mockMvc.perform(delete("/usuarios/excluir/{cpf}", cpf))
                .andExpect(status().isNoContent());

        assert !usuario.isAtivo();
    }
}
