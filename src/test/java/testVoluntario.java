import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import isst23.proyecto.model.Voluntario;
import isst23.proyecto.repository.VoluntarioRepository;

@SpringBootTest
public class testVoluntario {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Test
    public void registrarVoluntarioTest() {
        // Crear un nuevo voluntario de ejemplo
        Voluntario voluntario = new Voluntario("Homer", "Simpson", "123456789", "Homer@gmail.com", "cerveza", "calle 123", "Horario");

        // Guardar el voluntario en la base de datos
        Voluntario nuevoVoluntario = voluntarioRepository.save(voluntario);

        // Verificar que el voluntario se haya guardado correctamente
        assertNotNull(nuevoVoluntario);
        assertEquals(voluntario.getnombre(), nuevoVoluntario.getnombre());
        assertEquals(voluntario.getapellido(), nuevoVoluntario.getapellido());
        assertEquals(voluntario.gettelefono(), nuevoVoluntario.gettelefono());
        assertEquals(voluntario.getcorreo(), nuevoVoluntario.getcorreo());
        assertEquals(voluntario.getcontraseña(), nuevoVoluntario.getcontraseña());
        assertEquals(voluntario.getdireccion(), nuevoVoluntario.getdireccion());
        assertEquals(voluntario.getHorario(), nuevoVoluntario.getHorario());
    }

  
}
