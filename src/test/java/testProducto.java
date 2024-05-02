import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import isst23.proyecto.controller.ProductoController;
import isst23.proyecto.model.Producto;
import isst23.proyecto.model.Vendedor;
import isst23.proyecto.repository.ProductoRepository;
import isst23.proyecto.repository.VendedorRepository;

public class testProducto {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductsByVendedorId() {
    
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Producto 1", "Descripción 1", 10.0, 5, "foto1.jpg"));
        productos.add(new Producto("Producto 2", "Descripción 2", 15.0, 8, "foto2.jpg"));

        when(productoRepository.findByVendedorId(any(Long.class))).thenReturn(productos);

        ResponseEntity<List<Producto>> response = productoController.getProductsByVendedorId(1L);

        verify(productoRepository, times(1)).findByVendedorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

   /*  @Test
    public void testCreateProducto() {
   
        Producto producto = new Producto("Producto 1", "Descripción 1", 10.0, 5, "foto1.jpg");

        when(vendedorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Vendedor()));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.createProducto(producto);

        verify(productoRepository, times(1)).save(any(Producto.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Producto 1", response.getBody().getNombre());
    }*/
}
