package edu.interview.backend.controller;

import edu.interview.backend.model.Marca;
import edu.interview.backend.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas() {
        List<Marca> marcas = marcaService.findAll();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Marca> saveMarca(@RequestBody Marca marca) {
        Marca marcaSaved = marcaService.save(marca);
        return new ResponseEntity<>(marcaSaved, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable("id") Long idMarca) {
        marcaService.deleteById(idMarca);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMarca(@PathVariable("id") Long idMarca, @RequestBody Marca marcaRequest) {
        try {
            Marca marcaUpdated = marcaService.update(idMarca, marcaRequest);
            return new ResponseEntity<>(marcaUpdated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
