import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import isst23.proyecto.controller.CompradorController;
import isst23.proyecto.model.Comprador;
import isst23.proyecto.model.Pedido;
import isst23.proyecto.repository.CompradorRepository;
import isst23.proyecto.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class testComprador {

    @Mock
    private CompradorRepository compradorRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private CompradorController compradorController;

    @Test
    public void testRegistrarComprador() {
        Comprador comprador = new Comprador();
        comprador.setnombre("Nombre");
        comprador.setcorreo("correo@example.com");
        when(compradorRepository.save(any(Comprador.class))).thenReturn(comprador);

        Comprador result = compradorController.registrarComprador(comprador);

        assertNotNull(result);
        assertEquals("Nombre", result.getnombre());
        assertEquals("correo@example.com", result.getcorreo());
    }

    @Test
    public void testIniciarSesionCredencialesCorrectas() {
        Comprador comprador = new Comprador();
        comprador.setcorreo("correo@example.com");
        comprador.setcontraseña("contraseña");
        when(compradorRepository.findByCorreo("correo@example.com")).thenReturn(comprador);

        Comprador compradorLogin = new Comprador();
        compradorLogin.setcorreo("correo@example.com");
        compradorLogin.setcontraseña("contraseña");
        ResponseEntity<Map<String, String>> response = compradorController.iniciarSesion(compradorLogin);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("comprador", response.getBody().get("mensaje"));
    }

    @Test
    public void testObtenerCompradorPorId() {
        Comprador comprador = new Comprador();
        comprador.setId(1);
        comprador.setnombre("Nombre");
        when(compradorRepository.findById(1L)).thenReturn(Optional.of(comprador));

        ResponseEntity<Comprador> response = compradorController.obtenerCompradorPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nombre", response.getBody().getnombre());
    }

    
}