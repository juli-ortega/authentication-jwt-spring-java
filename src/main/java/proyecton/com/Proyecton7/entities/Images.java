package proyecton.com.Proyecton7.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author chris
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter    //Agrego lombok
public class Images {
    @Id
    @GeneratedValue(generator = "uuid") //Genero valor de forma automática.
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idImagen;   //cambio nombre de variable: id reemplazado por id imagen - gise

    private String mime;

    private String nombre;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;


}