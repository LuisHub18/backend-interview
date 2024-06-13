package edu.interview.backend.repository;

import edu.interview.backend.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long>{ }
