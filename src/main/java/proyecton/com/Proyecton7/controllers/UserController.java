package proyecton.com.Proyecton7.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import proyecton.com.Proyecton7.Jwt.JwtService;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    //@PreAuthorize("isAuthenticated()")
    private final JwtService jwtService;

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, ModelMap modelMap) {
        // Recuperar la cookie que contiene el token JWT
        Cookie cookie = WebUtils.getCookie(request, "jwt-token");

        if (cookie != null) {
            String token = cookie.getValue();

            // Verificar y decodificar el token JWT
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("586E3272357538782F413F4428472B4B6250655368566B597033733676397924".getBytes())) // Reemplaza "your_secret_key" con tu clave secreta
                        .build()
                        .parseClaimsJws(token);

                // Extraer información del token JWT (por ejemplo, nombre de usuario)
                String username = jws.getBody().getSubject();
                modelMap.put("username", username);

                // Realizar acciones específicas para el usuario autenticado
                return "userHome.html";
            } catch (Exception e) {
                // Manejar el error de validación del token JWT
                return "Error al validar el token JWT";
            }
        } else {
            // No se encontró la cookie, probablemente el usuario no está autenticado
            return "login.html";
        }
    }
}

