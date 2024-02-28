package proyecton.com.Proyecton7.auth;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import proyecton.com.Proyecton7.Jwt.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @PostMapping(value = "login")
    public RedirectView login(@ModelAttribute LoginRequest request, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = authService.login(request).token;
        response.addHeader("Authorization", "Bearer " + token);
        System.out.println("token: " + token);
        return new RedirectView("/");
    }

    @PostMapping(value = "register")
    public RedirectView register(@ModelAttribute RegisterRequest request,HttpServletResponse response) throws Exception {
        authService.register(request);
        String requestString = URLEncoder.encode(String.valueOf(request), StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("user", requestString);
        response.addCookie(cookie);
        return new RedirectView("/");
    }

}
