package isst23.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import isst23.proyecto.model.Producto;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.ProductoRepository;
import isst23.proyecto.repository.VendedorRepository;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired   
    private VendedorRepository vendedorRepository;

    // @GetMapping
    // public ResponseEntity<List<Producto>> getAllProductos() {
    //     List<Producto> productos = productoRepository.findAll();
    //     return ResponseEntity.ok().body(productos);
    // }
    @GetMapping
    public ResponseEntity<List<Producto>> getProductsByVendedorId(@RequestParam(required = false) Long idVendedor) {
        List<Producto> productos;
        if (idVendedor != null) {
            productos = productoRepository.findByVendedorId(idVendedor);
        } else {
            productos = productoRepository.findAll();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Long vendedorId = Long.valueOf(producto.getVendedor().getId());
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(vendedorId);
        if (!vendedorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Vendedor vendedor = vendedorOptional.get();
        producto.setVendedor(vendedor);
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        // Verificar si el producto existe en la base de datos
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            // Obtener el producto existente
            Producto productoExistente = optionalProducto.get();
            
            // Actualizar los campos del producto con los nuevos valores
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setStock(productoActualizado.getStock());
    
            // Guardar el producto actualizado en la base de datos
            Producto productoGuardado = productoRepository.save(productoExistente);
            
            // Devolver el producto actualizado en la respuesta
            return ResponseEntity.ok(productoGuardado);
        } else {
            // Si el producto no existe, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
    // Buscar el producto por su ID en la base de datos
    Optional<Producto> optionalProducto = productoRepository.findById(id);
    if (optionalProducto.isPresent()) {
        // Si se encuentra el producto, devolverlo en la respuesta
        Producto producto = optionalProducto.get();
        return ResponseEntity.ok(producto);
    } else {
        // Si no se encuentra el producto, devolver un error 404
        return ResponseEntity.notFound().build();
    }
}

    // Endpoint para eliminar un producto por su ID
     @DeleteMapping("/{id}")
     public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        try {
            // Buscar el producto en la base de datos por su ID
            Optional<Producto> optionalProducto = productoRepository.findById(id);
            if (optionalProducto.isPresent()) {
                // Si se encuentra el producto, eliminarlo de la base de datos
                productoRepository.delete(optionalProducto.get());
                return ResponseEntity.ok("Producto eliminado exitosamente");
            } else {
                // Si no se encuentra el producto, devolver un mensaje de error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún producto con el ID proporcionado");
            }
        } catch (Exception e) {
            // Si ocurre algún error durante la eliminación del producto, devolver un mensaje de error interno del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
        }
     }

}
