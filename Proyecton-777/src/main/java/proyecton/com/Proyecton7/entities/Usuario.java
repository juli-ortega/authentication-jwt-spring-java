package proyecton.com.Proyecton7.entities;
//lol
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecton.com.Proyecton7.enumeraciones.Roles;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String dni;
    private String nombreCompleto;
    private String email;
    private String contraseña;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    private Boolean alta;
}
