import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isst23.proyecto.ProyectoApplication;
import isst23.proyecto.model.Comprador;
import isst23.proyecto.model.Pedido;
import isst23.proyecto.repository.CompradorRepository;
import isst23.proyecto.repository.PedidoRepository;

@SpringBootTest(classes = ProyectoApplication.class)
public class testPedido {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Test
    public void addPedidoTest() {
        
        Comprador comprador = new Comprador();
        comprador.setId((int) 1L);
        comprador.setnombre("Ejemplo Comprador");
        compradorRepository.save(comprador);

        Pedido pedido = new Pedido("Pedido de prueba", "2024-05-01 10:00:00", 1L);
        pedido.setComprador(comprador);

        Pedido nuevoPedido = pedidoRepository.save(pedido);

        assertTrue(nuevoPedido.getId() != null);
        assertEquals(pedido.getDescripcion(), nuevoPedido.getDescripcion());
        assertEquals(pedido.gethoraRecogida(), nuevoPedido.gethoraRecogida());
        assertEquals(pedido.getVendedorId(), nuevoPedido.getVendedorId());
        assertEquals(pedido.getComprador(), nuevoPedido.getComprador());
    }

    @Test
    public void getPedidosByVendedorIdTest() {
        Pedido pedido1 = new Pedido("Pedido 1", "2024-05-01 10:00:00", 1L);
        Pedido pedido2 = new Pedido("Pedido 2", "2024-05-02 12:00:00", 1L);
        Pedido pedido3 = new Pedido("Pedido 3", "2024-05-03 15:00:00", 1L);

        pedidoRepository.saveAll(List.of(pedido1, pedido2, pedido3));

        List<Pedido> pedidos = pedidoRepository.findByVendedorId(1L);

        assertEquals(3, pedidos.size());
    }

   /*@Test
    public void getPedidoByIdTest() {
      
        Pedido pedido = new Pedido("Pedido de prueba", "2024-05-01 10:00:00", 1L);
        Pedido nuevoPedido = pedidoRepository.save(pedido);

        Pedido pedidoRecuperado = pedidoRepository.findById(nuevoPedido.getId()).orElse(null);

        assertEquals(nuevoPedido, pedidoRecuperado);
    }*/
}
