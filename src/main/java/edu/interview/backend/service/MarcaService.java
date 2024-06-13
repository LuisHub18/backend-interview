package edu.interview.backend.service;

import edu.interview.backend.model.Marca;
import edu.interview.backend.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca update (Long idMarca, Marca marca) {
        Optional<Marca> marcaOptional = marcaRepository.findById(idMarca);

        Marca marcaToUpdate = marcaOptional.orElseThrow(
                () -> new RuntimeException("Marca no encontrada con id: " + idMarca)
        );

        marcaToUpdate.setNombre(marca.getNombre());
        return marcaRepository.save(marcaToUpdate);
    }

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public void deleteById(Long idMarca) {
        marcaRepository.deleteById(idMarca);
    }
}
