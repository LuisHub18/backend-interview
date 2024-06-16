package edu.interview.backend.controller;

import edu.interview.backend.dto.ArticuloRequest;
import edu.interview.backend.model.Articulo;
import edu.interview.backend.service.ArticuloService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {
    private static final Logger logger = LoggerFactory.getLogger(ArticuloController.class);

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    private ResponseEntity<List<Articulo>> getArticulos() {
        logger.info("Obteniendo articulos");
        List<Articulo> articulos = articuloService.findAll();
        return new ResponseEntity<>(articulos, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> saveArticulo(@RequestBody ArticuloRequest articuloRequest) {
        logger.info("Creando articulo: {}", articuloRequest.getNombre());
        try {
            Articulo savedArticulo = articuloService.save(articuloRequest);
            return new ResponseEntity<>(savedArticulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error al crear articulo:", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteArticulo(@PathVariable("id") Long idArticulo) {
        logger.info("Eliminando articulo: {}", idArticulo);
        articuloService.deleteById(idArticulo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateArticulo(@PathVariable("id") Long idArticulo, @RequestBody ArticuloRequest articuloRequest) {
        logger.info("Actualizando articulo: {}", idArticulo);
        try {
            Articulo savedArticulo = articuloService.update(idArticulo, articuloRequest);
            return new ResponseEntity<>(savedArticulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error al actualizar articulo:", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
