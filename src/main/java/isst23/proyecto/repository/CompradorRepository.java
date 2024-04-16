package isst23.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isst23.proyecto.model.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {
    Comprador findByCorreo(String correo);
}

