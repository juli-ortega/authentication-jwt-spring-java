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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails email = userRepository.findUserByEmail(request.getEmail()).orElseThrow();

        String token = jwtService.getToken(email);

        return  AuthResponse.builder()
                .token(token)
                .build();
    }
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dni(request.getDni())
                .phone_number(request.getPhone_number())
                .alta(request.getAlta())
                .rol(Roles.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}