package isst23.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import isst23.proyecto.model.Comprador;
import isst23.proyecto.model.Pedido;
import isst23.proyecto.repository.PedidoRepository;
import isst23.proyecto.repository.CompradorRepository;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private CompradorRepository compradorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {
    int compradorId = pedido.getComprador().getId();
    Optional<Comprador> compradorOptional = compradorRepository.findById(compradorId);
    if (!compradorOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    Comprador comprador = compradorOptional.get();
    pedido.setComprador(comprador);

    Pedido nuevoPedido = pedidoRepository.save(pedido);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
}

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<Pedido>> getPedidosByVendedorId(@PathVariable long idVendedor) {
    List<Pedido> pedidos = pedidoRepository.findByVendedorId(idVendedor);
    if (pedidos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No se encontraron pedidos para este vendedor
    } else {
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}

@GetMapping("/{id}")
public ResponseEntity<Pedido> getPedidoById(@PathVariable long id) {
    // Buscar el pedido en la base de datos por su ID
    Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
    if (optionalPedido.isPresent()) {
        return ResponseEntity.ok(optionalPedido.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
