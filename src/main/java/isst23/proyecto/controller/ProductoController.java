package isst23.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import isst23.proyecto.model.Producto;
import isst23.proyecto.repository.ProductoRepository;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return ResponseEntity.ok().body(productos);
    }

@PostMapping
public ResponseEntity<Producto> createProducto(@RequestParam("producto") String productoJson, @RequestParam("foto") MultipartFile foto) {
    ObjectMapper objectMapper = new ObjectMapper();
    Producto producto;
    try {
        producto = objectMapper.readValue(productoJson, Producto.class);
        producto.setFoto(foto.getBytes());
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    Producto nuevoProducto = productoRepository.save(producto);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
}

}
