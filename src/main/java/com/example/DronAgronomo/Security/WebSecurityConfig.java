package com.example.DronAgronomo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Aseguramos que use texto plano
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> 
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/registro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/zonas/registro-zonas").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/dron/lecturas-dron").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dron/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/dron/*").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/tipo-equipos/buscar-por-rango-temperatura").hasAuthority("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
