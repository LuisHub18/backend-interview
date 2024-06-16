package edu.interview.backend.controller;

import edu.interview.backend.model.Marca;
import edu.interview.backend.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas() {
        logger.info("Obteniendo marcas");
        List<Marca> marcas = marcaService.findAll();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Marca> saveMarca(@RequestBody Marca marca) {
        logger.info("Creando marca: {}", marca.getNombre());
        Marca marcaSaved = marcaService.save(marca);
        return new ResponseEntity<>(marcaSaved, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable("id") Long idMarca) {
        logger.info("Eliminando marca: {}", idMarca);
        marcaService.deleteById(idMarca);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMarca(@PathVariable("id") Long idMarca, @RequestBody Marca marcaRequest) {
        logger.info("Actualizando marca: {}", idMarca);
        try {
            Marca marcaUpdated = marcaService.update(idMarca, marcaRequest);
            return new ResponseEntity<>(marcaUpdated, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error al actualizar marca:", e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
