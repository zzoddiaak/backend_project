package senla.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import senla.enums.RoleName;
import senla.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/resource-controller/**")).permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers(new  AntPathRequestMatcher("/api/v1/users/**")).hasAuthority(RoleName.CLIENT.getAuthority()));
        http.authorizeHttpRequests(request -> request.requestMatchers(new  AntPathRequestMatcher("/api/v1/psychologists/**")).hasAuthority(RoleName.PSYCHOLOGIST.getAuthority()));
        http.authorizeHttpRequests(request -> request.requestMatchers(new  AntPathRequestMatcher("/api/v1/orders/**")).hasAnyAuthority(RoleName.PSYCHOLOGIST.getAuthority(), RoleName.ADMIN.getAuthority()));
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
