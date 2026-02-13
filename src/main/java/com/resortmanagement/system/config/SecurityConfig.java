/*
TODO: SecurityConfig.java
Purpose:
 - Set up Spring Security: authentication provider, password encoder, JWT token filter if using JWT.
 - Define role-based access rules (ROLE_FRONTDESK, ROLE_MANAGER, ROLE_FINANCE, ROLE_ADMIN).
 - Integrate SecurityContext with AuditorAware (AuditorAwareImpl uses SecurityContextHolder).
Notes:
 - Protect webhook endpoints (payment) with signatures or shared secret.
 - Implement method-level security for service methods if needed.

File: config/SecurityConfig.java
*/
package com.resortmanagement.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Allow all requests (adjust as needed)
            );
        return http.build();
    }
}
