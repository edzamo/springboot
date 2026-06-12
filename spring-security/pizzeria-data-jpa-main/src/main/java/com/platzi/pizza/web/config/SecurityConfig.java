package com.platzi.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
           .authorizeHttpRequests()
           .anyRequest()
        .authenticated()
        .and()
        .httpBasic();

        return http.build();
    }

}
