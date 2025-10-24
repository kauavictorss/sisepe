package sisepe.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoUsuario extends JpaRepository<Usuario, String> {
    List<Usuario> findAllByAtivoTrue();
}