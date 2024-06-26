package isst23.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import isst23.proyecto.model.Voluntario;
import isst23.proyecto.repository.VoluntarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @PostMapping("/registro")
    public Voluntario registrarVoluntario(@RequestBody Voluntario voluntario) {
       return voluntarioRepository.save(voluntario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Voluntario voluntario) {
        // Buscar el comprador por correo electrónico y contraseña
        Voluntario voluntarioAutenticado = voluntarioRepository.findByCorreoAndContraseña(voluntario.getcorreo(), voluntario.getcontraseña());
        
        // Verificar si se encontró un comprador válido
        if (voluntarioAutenticado != null) {
            // Devolver una respuesta exitosa con un mensaje
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            // Devolver una respuesta de error con un mensaje
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}


