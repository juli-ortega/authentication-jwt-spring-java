package proyecton.com.Proyecton7.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import proyecton.com.Proyecton7.config.SecurityConfig;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String COOKIE_NAME = "auth_by_cookie";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(httpServletRequest.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
                .findFirst();

        if (cookieAuth.isPresent()) {
            SecurityContextHolder.getContext().setAuthentication(
                    new PreAuthenticatedAuthenticationToken(cookieAuth.get().getValue(), null));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}


