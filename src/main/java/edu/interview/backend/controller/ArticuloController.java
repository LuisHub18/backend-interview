package edu.interview.backend.controller;

import edu.interview.backend.dto.ArticuloRequest;
import edu.interview.backend.model.Articulo;
import edu.interview.backend.service.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    private ResponseEntity<List<Articulo>> getArticulos() {
        List<Articulo> articulos = articuloService.findAll();
        return new ResponseEntity<>(articulos, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> saveArticulo(@RequestBody ArticuloRequest articuloRequest) {
        try {
            Articulo savedArticulo = articuloService.save(articuloRequest);
            return new ResponseEntity<>(savedArticulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteArticulo(@PathVariable("id") Long idArticulo) {
        articuloService.deleteById(idArticulo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateArticulo(@PathVariable("id") Long idArticulo, @RequestBody ArticuloRequest articuloRequest) {
        try {
            Articulo savedArticulo = articuloService.update(idArticulo, articuloRequest);
            return new ResponseEntity<>(savedArticulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
