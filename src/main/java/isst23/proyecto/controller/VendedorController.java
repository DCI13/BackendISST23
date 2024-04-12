package isst23.proyecto.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.VendedorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @PostMapping("/registro")
    public Vendedor registrarVendedor(@RequestBody Vendedor vendedor) {
       return vendedorRepository.save(vendedor);
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Vendedor vendedor) {
        // Buscar el comprador por correo electrónico y contraseña
        Vendedor vendedorAutenticado = vendedorRepository.findByCorreoAndContraseña(vendedor.getcorreo(), vendedor.getcontraseña());
        
        // Verificar si se encontró un comprador válido
        if (vendedorAutenticado != null) {
            // Devolver una respuesta exitosa con un mensaje
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            // Devolver una respuesta de error con un mensaje
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
