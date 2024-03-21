package proyecton.com.Proyecton7.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import proyecton.com.Proyecton7.exceptions.MiException;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    @ResponseBody
    public String login(@RequestBody LoginRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        // Aquí generas y obtienes el token JWT
        String token = authService.login(request).token;

        // Crear una cookie HttpOnly y Secure para el token JWT
        Cookie cookie = new Cookie("jwt-token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/"); // Establecer el alcance de la cookie

        // Agregar la cookie a la respuesta HTTP
        httpResponse.addCookie(cookie);

        return "userHome.html";
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", e.getMessage()));
        }
    }




    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Eliminar la cookie que contiene el token JWT
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setMaxAge(0); // Establecer la expiración en cero para eliminar la cookie
        cookie.setPath("/"); // Establecer el alcance de la cookie

        response.addCookie(cookie);

        return "index.html";
    }

}
