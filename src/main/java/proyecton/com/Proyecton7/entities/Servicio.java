package proyecton.com.Proyecton7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import proyecton.com.Proyecton7.entities.Images;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Genera automáticamente el ID
    private Long id;
    private String description;
    private String name;
// Se establece la relación con la entidad Images
    @OneToOne
    private Images images;
    private Double price;
    private boolean highOrLow;//alta o baja
    private int Customer;//valoracion
    private String testimonialsOrReviews;//Opiniones de clientes anteriores sobre el servicio, si están disponibles.
    private String availability;// disponibilidad
    private Double estimatedTime; //tiempo estimado
    private String requirements; // requerimientos para acceder al servicio
    private String specificCharacteristics; //Cualquier otra característica única o distintiva del servicio que pueda influir en la decisión de compra del cliente.
}

