package com.mohsen.springtunes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWithCsrfCookieConfig {
    public static final String password = "{bcrypt}$2a$12$OpudCYfhwGO5mFQeB6bzdO0V7PpW2ay2yZxVKeJBmFaogfraFZ/He";
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder().username("john")
                .password(password)
                .roles("EMPLOYEE").build();
        UserDetails mary = User.builder().username("mary")
                .password(password)
                .roles("EMPLOYEE", "MANAGER").build();
        UserDetails susan = User.builder().username("susan")
                .password(password)
                .roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET).hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST).hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT).hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}