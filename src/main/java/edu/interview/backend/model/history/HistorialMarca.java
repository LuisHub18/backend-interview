package edu.interview.backend.model.history;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_marca")
public class HistorialMarca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int marcaId;
    private String nombre;
    private String operacion;
    private LocalDateTime fechaOperacion;

    public HistorialMarca() {
    }

    public HistorialMarca(int marcaId, String nombre, String operacion, LocalDateTime fechaOperacion) {
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.operacion = operacion;
        this.fechaOperacion = fechaOperacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
}
