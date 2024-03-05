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


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/")
    public String user(HttpServletRequest request, ModelMap modelMap) {
        //Obtiene la cookie del cliente
        Cookie cookie = WebUtils.getCookie(request, "jwt-token");

        if (cookie != null) {
            String token = cookie.getValue();

            // Verifica el token
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("586E3272357538782F413F4428472B4B6250655368566B597033733676397924".getBytes()))
                        .build()
                        .parseClaimsJws(token);

                // Extrae el nombre de la persona para saludarla
                Claims claims = jws.getBody();
                modelMap.put("firstname", claims.get("firstname"));

                return "userHome.html";
            } catch (Exception e) {
                // Manejar el error de validación del token JWT
                return "redirect:/#myModal";

            }
        } else {
            //Si no tiene el token en la cookie, retorna al login
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/papu")
    public String prueba(HttpServletRequest request, ModelMap modelMap) {
        return check_user_login("index.html",request,modelMap);
    }

    @GetMapping("/home")
    public String profile(HttpServletRequest request, ModelMap modelMap) {
        //Obtiene la cookie del cliente
        return check_user_login("userHome.html", request,modelMap);
    }
    // CHECK IF USER IS LOGGED IN
    public String check_user_login(String renderpage, HttpServletRequest request, ModelMap modelMap){
        //Obtiene la cookie del cliente
        Cookie cookie = WebUtils.getCookie(request, "jwt-token");

        if (cookie != null) {
            String token = cookie.getValue();

            // Verifica el token
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("586E3272357538782F413F4428472B4B6250655368566B597033733676397924".getBytes()))
                        .build()
                        .parseClaimsJws(token);

                // Extrae el nombre de la persona para saludarla
                Claims claims = jws.getBody();
                modelMap.put("firstname", claims.get("firstname"));

                return renderpage;
            } catch (Exception e) {
                // Manejar el error de validación del token JWT
                return "redirect:/#myModal";

            }
        } else {
            //Si no tiene el token en la cookie, retorna al login
            return "redirect:/auth/login";
        }
    }
}

