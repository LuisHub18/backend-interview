package edu.interview.backend.service;

import edu.interview.backend.dto.ArticuloRequest;
import edu.interview.backend.model.Articulo;
import edu.interview.backend.model.Modelo;
import edu.interview.backend.repository.ArticuloRepository;
import edu.interview.backend.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final ModeloRepository modeloRepository;

    public ArticuloService(ArticuloRepository articuloRepository, ModeloRepository modeloRepository) {
        this.articuloRepository = articuloRepository;
        this.modeloRepository = modeloRepository;
    }

    public Articulo save(ArticuloRequest articuloRequest) {
        Long modeloId = articuloRequest.getModeloId();
        Optional<Modelo> modeloOptional = modeloRepository.findById(modeloId);

        Modelo modelo = modeloOptional.orElseThrow(
                () -> new RuntimeException("Modelo no encontrado con id: " + modeloId)
        );

        Articulo articulo = new Articulo();
        articulo.setNombre(articuloRequest.getNombre());
        articulo.setDescripcion(articuloRequest.getDescripcion());
        articulo.setPrecio(articuloRequest.getPrecio());
        articulo.setCantidad(articuloRequest.getCantidad());
        articulo.setFechaCreacion(LocalDate.now());
        articulo.setModelo(modelo);

        return articuloRepository.save(articulo);
    }

    public Articulo update(Long id, ArticuloRequest articuloRequest) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);

        Articulo articulo = articuloOptional.orElseThrow(
                () -> new RuntimeException("Articulo no encontrado con id: " + id)
        );

        articulo.setNombre(articuloRequest.getNombre());
        articulo.setDescripcion(articuloRequest.getDescripcion());
        articulo.setPrecio(articuloRequest.getPrecio());
        articulo.setCantidad(articuloRequest.getCantidad());
        articulo.setFechaCreacion(LocalDate.now());

        Long modeloId = articuloRequest.getModeloId();
        Optional<Modelo> modeloOptional = modeloRepository.findById(modeloId);

        Modelo modelo = modeloOptional.orElseThrow(
                () -> new RuntimeException("Modelo no encontrado con id: " + modeloId)
        );
        articulo.setModelo(modelo);

        return articuloRepository.save(articulo);
    }

    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }

    public void deleteById(Long idArticulo) {
        articuloRepository.deleteById(idArticulo);
    }
}
