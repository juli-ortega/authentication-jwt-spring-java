package proyecton.com.Proyecton7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List; // Cambio de ArrayList a List

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Genera automáticamente el ID
    private Long id;
    private String descripcion;
    @OneToMany(mappedBy = "service") // Se establece la relación con la entidad Images
    private List<Images> images; // Cambio de ArrayList a List
    private Double price;
    private boolean highOrLow;//alta o baja
    private int Customer;//valoracion
    private String testimonialsOrReviews;//Opiniones de clientes anteriores sobre el servicio, si están disponibles.
    private String availability;// disponibilidad
    private Double estimatedTime; //tiempo estimado
    private String requirements; // requerimientos para acceder al servicio
    private String specificCharacteristics; //Cualquier otra característica única o distintiva del servicio que pueda influir en la decisión de compra del cliente.
}

