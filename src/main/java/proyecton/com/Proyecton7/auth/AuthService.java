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
import proyecton.com.Proyecton7.exceptions.MiException;
import proyecton.com.Proyecton7.repositories.UserRepository;

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

        String token = jwtService.getToken((CustomUserDetails) email);

        return  AuthResponse.builder()
                .token(token)
                .build();
    }
    public AuthResponse register(RegisterRequest request) throws MiException {

        validate(request);

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



    private void validate(RegisterRequest request) throws MiException {
        // Extraer los campos del objeto RegisterRequest
        String dni = request.getDni();
        String password = request.getPassword();
        String firstname = request.getFirstname();
        String lastname = request.getLastname();
        String email = request.getEmail();
        String phone_number = request.getPhone_number();

        // Validar que los campos no sean nulos o vacíos
        if (dni == null || dni.isEmpty()) {
            throw new MiException("El campo DNI no debe ser nulo o vacío.");
        }
        if (password == null || password.isEmpty()) {
            throw new MiException("El campo password no debe ser nulo o vacío.");
        }
        if (firstname == null || firstname.isEmpty()) {
            throw new MiException("El campo firstname no debe ser nulo o vacío.");
        }
        if (lastname == null || lastname.isEmpty()) {
            throw new MiException("El campo lastname no debe ser nulo o vacío.");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El campo email no debe ser nulo o vacío.");
        }
        if (phone_number == null || phone_number.isEmpty()) {
            throw new MiException("El campo phone_number no debe ser nulo o vacío.");
        }

        // Validar longitud del DNI
        if (dni.length() < 7 || dni.length() > 9) {
            throw new MiException("El campo DNI debe tener entre 7 y 9 caracteres.");
        }

        // Validar contraseña
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            throw new MiException("La contraseña debe contener al menos una minúscula, una mayúscula y un número.");
        }

        // Validar formato de email
        if (!email.contains("@") || !email.endsWith(".com")) {
            throw new MiException("El campo email debe tener un formato válido (ejemplo@example.com).");
        }
    }



}