import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import isst23.proyecto.controller.VendedorController;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.VendedorRepository;




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
    public void testCrearVendedor() {
        
        // Crear un nuevo vendedor
        Vendedor vendedor = new Vendedor();
        vendedor.setnombre("Homer Simpson");
        vendedor.setcorreo("homer@gmail.com");
        vendedor.setcontraseña("1234");
        vendedor.setdireccion("Calle amarilla");
        vendedorRepository.save(vendedor);
        // Verificar que se haya creado correctamente
        assertNotNull(vendedor);
        assertEquals("Homer Simpson", vendedor.getnombre());
        assertEquals("juan.perez@mail.com", vendedor.getcorreo());
        assertEquals("1234", vendedor.getcontraseña());
        assertEquals("Calle amarilla", vendedor.getdireccion());
    }
}

