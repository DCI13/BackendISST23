package isst23.proyecto.model;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Long stock;
    private byte[] foto;

    // Constructor
    public Producto() {}

    public Producto(String nombre, String descripcion, Float precio, Long stock, byte[] foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.foto = foto;
    }
    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public Long getStock() {
        return stock;
    }

    public byte[] getFoto() {
        return foto;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}