package isst23.proyecto.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.VendedorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping
    public ResponseEntity<List<Vendedor>> getAllVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return ResponseEntity.ok().body(vendedores);
    }
    
    @PostMapping("/registro")
    public Vendedor registrarVendedor(@RequestBody Vendedor vendedor) {
       return vendedorRepository.save(vendedor);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> iniciarSesion(@RequestBody Vendedor vendedor) {
        Map<String, String> response = new HashMap<>();

        // Buscar al vendedor por correo electrónico
        Vendedor vendedorAutenticado = vendedorRepository.findByCorreo(vendedor.getcorreo());
        
        // Verificar si se encontró un vendedor con ese correo electrónico
        if (vendedorAutenticado != null) {
            // Verificar si la contraseña coincide
            if (vendedorAutenticado.getcontraseña().equals(vendedor.getcontraseña())) {
                // Si las credenciales son correctas, devolver un mensaje exitoso con el tipo de usuario y el ID
                response.put("mensaje", "vendedor");
                response.put("id", String.valueOf(vendedorAutenticado.getId()));
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
        public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Long id) {
        // Buscar el vendedor por su ID en la base de datos
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(id);
        if (optionalVendedor.isPresent()) {
            // Si se encuentra el vendedor, devolverlo en la respuesta
            Vendedor vendedor = optionalVendedor.get();
            return ResponseEntity.ok(vendedor);
        } else {
            // Si no se encuentra el vendedor, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }
}
