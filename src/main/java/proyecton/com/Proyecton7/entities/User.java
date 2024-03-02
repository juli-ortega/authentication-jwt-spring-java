package proyecton.com.Proyecton7.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import proyecton.com.Proyecton7.auth.CustomUserDetails;
import proyecton.com.Proyecton7.enumeraciones.Roles;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "dni"})
})
@Component
public class User implements CustomUserDetails {

    @Id
    @GeneratedValue()
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime alta;
    @Column(nullable = false)
    private String dni;
    @Column(nullable = false)
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String phone_number;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    protected void onCreate() {
        alta = LocalDateTime.now(); // Establecer la fecha de alta como el momento actual al crear un nuevo usuario
    }

}
