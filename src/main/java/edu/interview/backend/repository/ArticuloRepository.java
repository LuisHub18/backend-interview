package edu.interview.backend.repository;

import edu.interview.backend.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List<Articulo> findByModeloId(Long modeloId);
}
