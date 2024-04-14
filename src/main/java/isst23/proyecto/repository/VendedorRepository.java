package isst23.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isst23.proyecto.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Vendedor findByCorreo(String correo);
}
