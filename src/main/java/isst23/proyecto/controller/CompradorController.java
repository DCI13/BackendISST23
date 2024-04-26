package isst23.proyecto.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import isst23.proyecto.model.Comprador;
import isst23.proyecto.model.Pedido;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.CompradorRepository;
import isst23.proyecto.repository.PedidoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comprador")
public class CompradorController {

    @Autowired
    private CompradorRepository compradorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/registro")
    public Comprador registrarComprador(@RequestBody Comprador comprador) {
        return compradorRepository.save(comprador);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> iniciarSesion(@RequestBody Comprador comprador) {
        Map<String, String> response = new HashMap<>();

        // Buscar al comprador por correo electrónico
        Comprador compradorAutenticado = compradorRepository.findByCorreo(comprador.getcorreo());
        
        // Verificar si se encontró un comprador con ese correo electrónico
        if (compradorAutenticado != null) {
            // Verificar si la contraseña coincide
            if (compradorAutenticado.getcontraseña().equals(comprador.getcontraseña())) {
                // Si las credenciales son correctas, devolver un mensaje exitoso con el tipo de usuario y el ID
                response.put("mensaje", "comprador");
                response.put("id", String.valueOf(compradorAutenticado.getId()));
                return ResponseEntity.ok(response);
            } else {
                // Si la contraseña no coincide, devolver un mensaje de error
                response.put("error", "Credenciales incorrectas");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            // Si no se encontró ningún vendedor con ese correo electrónico, devolver un mensaje de error
            response.put("error", "Credenciales incorrectas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprador> obtenerCompradorPorId(@PathVariable Long id) {
        // Buscar el vendedor por su ID en la base de datos
        Optional<Comprador> optionalComprador = compradorRepository.findById(id);
        if (optionalComprador.isPresent()) {
            // Si se encuentra el vendedor, devolverlo en la respuesta
            Comprador comprador = optionalComprador.get();
            return ResponseEntity.ok(comprador);
        } else {
            // Si no se encuentra el vendedor, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vulnerable/{vulnerable}")
    public ResponseEntity<List<Pedido>> getPedidosByCompradorVulnerable(@PathVariable String vulnerable) {
    List<Comprador> compradores = compradorRepository.findByVulnerable(vulnerable);
    List<Pedido> pedidos = new ArrayList<>();
    for (Comprador comprador : compradores) {
        pedidos.addAll(pedidoRepository.findByCompradorId(comprador.getId()));
    }
    if (pedidos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No se encontraron pedidos para estos compradores
    } else {
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}
}
