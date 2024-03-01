package proyecton.com.Proyecton7.auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecton.com.Proyecton7.enumeraciones.Roles;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String dni;
    String password;
    String firstname;
    String lastname;
    String email;
    String phone_number;
    LocalDateTime alta;
    Roles rol = Roles.USER;
}
