package isst23.proyecto.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("Valor de isVulnerable recibido desde el frontend: " + comprador.isVulnerable());
        boolean vulnerable = comprador.isVulnerable() ? true : false;
    
        // Establecer el valor de vulnerable en el objeto comprador antes de guardarlo en la base de datos
       comprador.setVulnerable(vulnerable);
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
}
