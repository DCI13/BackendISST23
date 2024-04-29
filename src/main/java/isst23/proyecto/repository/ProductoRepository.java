package isst23.proyecto.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import isst23.proyecto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByVendedorId(Long idVendedor);

}
