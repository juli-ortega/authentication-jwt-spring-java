package proyecton.com.Proyecton7.entities;

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
public class User {

    @Id
    private String dni;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phone_number;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    private Boolean alta;

}
