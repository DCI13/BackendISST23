package isst23.proyecto.model;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "voluntarios")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contraseña;
    private String direccion;
    private String horario;

    @JsonIgnore

    public Voluntario() {
    }

    public Voluntario(String nombre, String apellido, String telefono, String correo, String contraseña, String direccion, String horario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.horario = horario;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getnombre() {
        return this.nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getapellido() {
        return this.apellido;
    }

    public void setapellido(String apellido) {
        this.apellido = apellido;
    }

    public String gettelefono() {
        return this.telefono;
    }

    public void settelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getcorreo() {
        return this.correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String getcontraseña() {
        return this.contraseña;
    }

    public void setcontraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getdireccion() {
        return this.direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}

