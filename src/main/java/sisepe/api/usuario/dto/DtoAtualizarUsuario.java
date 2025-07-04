package sisepe.api.usuario.dto;

import sisepe.api.usuario.Usuario;

public record DtoAtualizarUsuario(String nome, String email, String cpf) {
    public DtoAtualizarUsuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getCpf());
    }
}
