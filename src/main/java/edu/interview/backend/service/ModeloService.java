package edu.interview.backend.service;

import edu.interview.backend.dto.ModeloRequest;
import edu.interview.backend.model.Marca;
import edu.interview.backend.model.Modelo;
import edu.interview.backend.repository.MarcaRepository;
import edu.interview.backend.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloService{

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloService(ModeloRepository modeloRepository, MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Modelo> findByMarcaId(Long idMarca) {
        return modeloRepository.findByMarcaId(idMarca);
    }

    public Modelo save(ModeloRequest modeloRequest) {
        Long marcaId = modeloRequest.getMarcaId();
        Optional<Marca> marcaOptional = marcaRepository.findById(marcaId);

        Marca marca = marcaOptional.orElseThrow(
                () -> new RuntimeException("Marca no encontrada con id: " + marcaId)
        );

        Modelo modelo = new Modelo();
        modelo.setNombre(modeloRequest.getNombre());
        modelo.setMarca(marca);

        return modeloRepository.save(modelo);
    }

    public Modelo update(Long id, ModeloRequest modeloRequest) {
        Optional<Modelo> modeloOptional = modeloRepository.findById(id);

        Modelo modelo = modeloOptional.orElseThrow(
                () -> new RuntimeException("Modelo no encontrado con id: " + id)
        );
        modelo.setNombre(modeloRequest.getNombre());

        Long marcaId = modeloRequest.getMarcaId();
        Optional<Marca> marcaOptional = marcaRepository.findById(marcaId);
        Marca marca = marcaOptional.orElseThrow(
                () -> new RuntimeException("Marca no encontrada con id: " + marcaId)
        );

        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public void deleteById(Long idModelo) {
        modeloRepository.deleteById(idModelo);
    }
}
