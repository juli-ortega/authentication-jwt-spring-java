package proyecton.com.Proyecton7.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyecton.com.Proyecton7.Jwt.JwtService;
import proyecton.com.Proyecton7.entities.User;
import proyecton.com.Proyecton7.enumeraciones.Roles;
import proyecton.com.Proyecton7.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return  AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) throws Exception {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .first_name(request.getFirstname())
                    .last_name(request.getLastname())
                    .email(request.getEmail())
                    .dni(request.getDni())
                    .phone_number(request.getPhone_number())
                    .rol(Roles.USER)
                    .build();
            System.out.println(user);
            userRepository.save(user);
            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }

    }
}
