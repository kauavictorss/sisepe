package sisepe.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUsuario extends JpaRepository<Usuario, String> {
}
