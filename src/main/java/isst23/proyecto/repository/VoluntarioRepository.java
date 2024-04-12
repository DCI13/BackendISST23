package isst23.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isst23.proyecto.model.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Integer> {
    Voluntario findByCorreoAndContraseña(String correo, String contraseña);
}
