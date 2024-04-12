package isst23.proyecto.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import isst23.proyecto.model.Comprador;
import isst23.proyecto.repository.CompradorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comprador")
public class CompradorController {

    @Autowired
    private CompradorRepository compradorRepository;

    @PostMapping("/registro")
    public Comprador registrarComprador(@RequestBody Comprador comprador) {
       return compradorRepository.save(comprador);
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Comprador comprador) {
        // Buscar el comprador por correo electrónico y contraseña
        Comprador compradorAutenticado = compradorRepository.findByCorreoAndContraseña(comprador.getcorreo(), comprador.getcontraseña());
        
        // Verificar si se encontró un comprador válido
        if (compradorAutenticado != null) {
            // Devolver una respuesta exitosa con un mensaje
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            // Devolver una respuesta de error con un mensaje
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
