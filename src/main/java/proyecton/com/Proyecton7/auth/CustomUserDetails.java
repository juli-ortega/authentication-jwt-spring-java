package proyecton.com.Proyecton7.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {

    String getEmail();
    String getFirstname();
    String getPassword();

    @Override
    default String getUsername() {
        return getEmail(); // Devuelve el correo electrónico como nombre de usuario
    }
}
