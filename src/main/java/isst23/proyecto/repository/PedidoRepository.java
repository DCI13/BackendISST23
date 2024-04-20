package isst23.proyecto.repository;

import isst23.proyecto.model.Pedido;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByVendedorId(long vendedorId);

    Collection<? extends Pedido> findByCompradorId(int id);
}
