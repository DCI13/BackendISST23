package isst23.proyecto.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "vendedores")
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contraseña;
    private String direccion;
    private String tienda;
    private String horario;
    private String imagen;
    @JsonIgnore

    public Vendedor() {
    }

    public Vendedor(String nombre, String apellido, String telefono, String correo, String contraseña, String direccion, String tienda, String horario, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.tienda = tienda;
        this.horario = horario;
        this.imagen = imagen;
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

    public String gettienda() {
        return this.tienda;
    }

    public void settienda(String tienda) {
        this.tienda = tienda;
    }

    public String gethorario() {
        return this.horario;
    }

    public void sethorario(String horario) {
        this.horario = horario;
    }

    public String  getimagen() {
        return this.imagen;
    }

    public void setimagen(String imagen) {
        this.imagen = imagen;
    }

}
