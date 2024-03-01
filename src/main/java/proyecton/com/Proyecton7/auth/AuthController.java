package proyecton.com.Proyecton7.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
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

        return "{\"message\": \"Login successful\"}";
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/logout")
    public RedirectView logout(HttpServletResponse response) {
        // Eliminar la cookie que contiene el token JWT
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setMaxAge(0); // Establecer la expiración en cero para eliminar la cookie
        cookie.setPath("/"); // Establecer el alcance de la cookie

        response.addCookie(cookie);

        return new RedirectView("/");
    }

}
