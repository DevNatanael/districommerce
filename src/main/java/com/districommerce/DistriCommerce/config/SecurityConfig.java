package com.districommerce.DistriCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desabilita CSRF para testes
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // libera tudo
                )
                .httpBasic(Customizer.withDefaults()); // habilita modo básico (sem efeito já que tudo está liberado)

        return http.build();
    }
}
