import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import isst23.proyecto.ProyectoApplication;
import isst23.proyecto.controller.VendedorController;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.VendedorRepository;


@SpringBootTest(classes = ProyectoApplication.class)

public class testVendedor {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorController vendedorController;

    @Test
    void testRegistrarVendedor() {
       
        Vendedor vendedor = new Vendedor("Nombre", "Apellido", "123456789", "correo@ejemplo.com", "contraseña", "Dirección", "Tienda", "Horario", "Imagen");

        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);

        Vendedor vendedorRegistrado = vendedorController.registrarVendedor(vendedor);
        assertEquals(vendedor, vendedorRegistrado);
    }

    

     /*@Test
    public void testCrearVendedor() {
        
        
        Vendedor vendedor = new Vendedor();
        vendedor.setnombre("Homer Simpson");
        vendedor.setcorreo("homer@gmail.com");
        vendedor.setcontraseña("1234");
        vendedor.setdireccion("Calle amarilla");
        vendedorRepository.save(vendedor);
        
        assertNotNull(vendedor);
        assertEquals("Homer Simpson", vendedor.getnombre());
        assertEquals("juan.perez@mail.com", vendedor.getcorreo());
        assertEquals("1234", vendedor.getcontraseña());
        assertEquals("Calle amarilla", vendedor.getdireccion());
    }*/
}

