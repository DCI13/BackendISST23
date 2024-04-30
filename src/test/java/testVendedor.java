import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import isst23.proyecto.controller.VendedorController;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.VendedorRepository;


import java.util.Map;

@SpringBootTest
public class testVendedor {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorController vendedorController;

    @Test
    void testRegistrarVendedor() {
        // Crear un objeto de vendedor para la prueba
        Vendedor vendedor = new Vendedor("Nombre", "Apellido", "123456789", "correo@ejemplo.com", "contraseña", "Dirección", "Tienda", "Horario", "Imagen");

        // Mockear el comportamiento del repositorio para retornar el vendedor creado
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);

        // Ejecutar el método del controlador
        Vendedor vendedorRegistrado = vendedorController.registrarVendedor(vendedor);

        // Verificar que el vendedor retornado sea el mismo que el vendedor creado
        assertEquals(vendedor, vendedorRegistrado);
    }

     @Test
    void testIniciarSesion_CredencialesCorrectas() {
        
        Vendedor vendedor = new Vendedor();
        vendedor.setcorreo("correo@ejemplo.com");
        vendedor.setcontraseña("contraseña");

        
        when(vendedorRepository.findByCorreo("correo@ejemplo.com")).thenReturn(vendedor);

        
        Vendedor vendedorLogin = new Vendedor();
        vendedorLogin.setcorreo("correo@ejemplo.com");
        vendedorLogin.setcontraseña("contraseña");

        
        ResponseEntity<Map<String, String>> response = vendedorController.iniciarSesion(vendedorLogin);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("vendedor", response.getBody().get("mensaje"));
    }

    
}

