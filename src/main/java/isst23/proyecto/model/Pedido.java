package isst23.proyecto.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String horaRecogida;
    private Long vendedorId;

    @ManyToOne
    @JsonBackReference("comprador-pedido")
    @JoinColumn(name = "comprador_id")
    private Comprador comprador;

    //Constructor
    public Pedido() {}

    public Pedido(String descripcion, String horaRecogida, Long vendedorId) {
        this.descripcion = descripcion;
        this.horaRecogida = horaRecogida;
        this.vendedorId = vendedorId;
    }

    public Long getId() {
        return id;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    } 

    public String getDescripcion() {
        return descripcion;
    }

    public String gethoraRecogida() {
        return horaRecogida;
    }
   
    // public Vendedor getVendedor() {
    //     return vendedorPedido;
    // }
    
    public Comprador getComprador() {
        return comprador;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void sethoraRecogida(String horaRecogida) {
        this.horaRecogida = horaRecogida;
    }

    // public void setVendedor(Vendedor vendedorPedido) {
    //     this.vendedorPedido = vendedorPedido;
    // }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}