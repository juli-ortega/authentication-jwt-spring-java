package proyecton.com.Proyecton7.auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecton.com.Proyecton7.enumeraciones.Roles;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String dni;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    String phone_number;
    Roles rol = Roles.USER;
}
