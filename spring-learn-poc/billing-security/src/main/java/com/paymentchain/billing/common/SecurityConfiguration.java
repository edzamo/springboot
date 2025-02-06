/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.billing.common;

import java.time.Duration;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author sotobotero
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
//    @Autowired
//private Environment env;

    private static final String[] NO_AUTH_LIST = {
       "*/api-docs/**",//
        "/swagger-ui/**",//
        "/swagger-ui/index.html",
        "/configuration/ui", //
        "/swagger-resources", //
        "/configuration/security", //   
        "/webjars/**", //
        "/login",
        "/h2-console/**"};
    
    
@Bean
public UserDetailsService users() {
    UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("qwerty"))
        .roles("USER")
        .build();
    
    UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("admin"))
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
}
      

@Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http 
          .httpBasic(withDefaults())
            .formLogin(withDefaults())          
          .authorizeHttpRequests((authz) -> authz
                .requestMatchers(NO_AUTH_LIST).permitAll()       
                // .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                //.requestMatchers("/*billing*/**").authenticated()
          .anyRequest().authenticated())
          .csrf().disable();
  return http.build();  
    }    
    

    
//    //This Handlers implement the CorsConfigurationSource interface in order to provide a CorsConfiguration for each request.
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration cc = new CorsConfiguration();
//        cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
//        cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        cc.setAllowedOrigins(Arrays.asList("/*"));
//        cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH"));
//        cc.addAllowedOriginPattern("*");
//        cc.setMaxAge(Duration.ZERO);
//        cc.setAllowCredentials(Boolean.TRUE);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", cc);
//        return source;
//    }

}
