package com.example.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests(auth -> auth
                        // public endpoint
                        .requestMatchers("/actuator/health").permitAll()

                        // secure actuator endpoints
//                        .requestMatchers("/actuator/**").authenticated()

                        // secure all other endpoints
//                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // simple login popup

        return http.build();
    }
}
