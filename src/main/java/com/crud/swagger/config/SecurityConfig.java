package com.crud.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Require authentication for Swagger UI and related endpoints
                .antMatchers(
                        "/swagger-ui/**",        // Swagger UI endpoints
                        "/swagger-resources/**", // Swagger resources
                        "/webjars/**",           // Webjars for Swagger UI
                        "/swagger-ui.html"       // Main Swagger UI page
                ).authenticated()   // Require authentication for Swagger UI

                // Secure all other endpoints - requires login for everything else
                .anyRequest().authenticated()  // Requires authentication for any other endpoint

                .and()
                .formLogin()
                .defaultSuccessUrl("/swagger-ui.html", true) // Redirect to Swagger UI after successful login
                .permitAll()                           // Allow everyone to access the login page
                .and()
                .logout()
                .permitAll();                           // Allow everyone to logout

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory user details manager with users and passwords
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // User details with encoded password (example user/password)
        userDetailsService.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))  // The encoded password
                .roles("USER")  // Assign 'USER' role
                .build());

        // Admin user details (admin/admin)
        userDetailsService.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))  // The encoded password
                .roles("ADMIN")  // Assign 'ADMIN' role
                .build());

        return userDetailsService;
    }
}
