package sisepe.api.usuario.dto;

import sisepe.api.usuario.Usuario;

public record DtoListagemUsuarios(String cpf, String nome, String email) {
    public DtoListagemUsuarios(Usuario usuario) {
        this(usuario.getCpf(), usuario.getNome(), usuario.getEmail());
    }
}
