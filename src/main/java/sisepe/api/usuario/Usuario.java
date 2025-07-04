package sisepe.api.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisepe.api.usuario.dto.DtoAtualizarUsuario;
import sisepe.api.usuario.dto.DtoCadUsuario;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario {
    @Id
    private String cpf;

    private String nome;
    private String email;

    public Usuario(DtoCadUsuario dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.email = dados.email();
    }

    public void atualizarDados(@Valid DtoAtualizarUsuario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }
}
