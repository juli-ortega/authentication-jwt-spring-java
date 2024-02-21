package proyecton.com.Proyecton7.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import proyecton.com.Proyecton7.Jwt.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @PostMapping(value = "login")
    public RedirectView login(@RequestBody LoginRequest request, HttpServletResponse response) {
        System.out.println(request.toString());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.getToken(userDetails);
        response.addHeader("Authorization", "Bearer " + token);
        return new RedirectView("/");
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) throws Exception {
            return ResponseEntity.ok(authService.register(request));
    }
}
