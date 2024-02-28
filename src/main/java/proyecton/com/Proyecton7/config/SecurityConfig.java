package proyecton.com.Proyecton7.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import proyecton.com.Proyecton7.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authRequest ->
                    {
                        try {
                            authRequest
                                    .requestMatchers("/auth/**", "/**", "/resources/static/css", "/resources/static/js").permitAll()
                                    .requestMatchers("/auth/login").permitAll()
                                    .anyRequest().authenticated()
                                    .and().formLogin()
                                    .loginPage("/auth/login")
                                    .loginProcessingUrl("/auth/logincheck")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/").permitAll()
                                    .and().logout().logoutUrl("/auth/login")
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/").permitAll().and().csrf().disable();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                )
            .sessionManagement(sessionManager->
                sessionManager
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
