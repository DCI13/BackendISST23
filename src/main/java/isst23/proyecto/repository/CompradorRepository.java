package isst23.proyecto.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import isst23.proyecto.model.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {
    Comprador findByCorreo(String correo);
    Optional<Comprador> findById(Long id);
    List<Comprador> findByVulnerable(String vulnerable);
}

