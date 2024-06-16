package edu.interview.backend.controller;

import edu.interview.backend.dto.ModeloRequest;
import edu.interview.backend.model.Modelo;
import edu.interview.backend.service.ModeloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/modelo")
public class ModeloController {
    private final ModeloService modeloService;
    private static final Logger logger = LoggerFactory.getLogger(ModeloController.class);

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> getModelos() {
        logger.info("Obteniendo modelos");
        List<Modelo> modelos = modeloService.findAll();
        return new ResponseEntity<>(modelos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Modelo>> getModelosByMarca(@PathVariable("id") Long idMarca) {
        logger.info("Obteniendo modelos por marca");
        List<Modelo> modelos = modeloService.findByMarcaId(idMarca);
        return new ResponseEntity<>(modelos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveModelo(@RequestBody ModeloRequest modeloRequest) {
        logger.info("Creando modelo: {}", modeloRequest.getNombre());
        try {
            Modelo savedModelo = modeloService.save(modeloRequest);
            return new ResponseEntity<>(savedModelo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error al crear modelo:", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteModelo(@PathVariable("id") Long idModelo) {
        logger.info("Eliminando modelo: {}", idModelo);
        modeloService.deleteById(idModelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateModelo(@PathVariable("id") Long idModelo, @RequestBody ModeloRequest modeloRequest) {
        logger.info("Actualizando modelo: {}", idModelo);
        try {
            Modelo savedModelo = modeloService.update(idModelo, modeloRequest);
            return new ResponseEntity<>(savedModelo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error al actualizar modelo:", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
