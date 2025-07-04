package sisepe.api.secao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoSecao extends JpaRepository<Secao, Integer> {
    List<Secao> findByZonaNumero(int numZona);
}
