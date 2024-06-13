package edu.interview.backend.repository;

import edu.interview.backend.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{
    List<Modelo> findByMarcaId(Long marcaId);
}
