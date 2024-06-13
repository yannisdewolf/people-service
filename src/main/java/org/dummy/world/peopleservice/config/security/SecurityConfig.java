package org.dummy.world.peopleservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/actuator/togglz/**").permitAll()
                        .requestMatchers("/togglz-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cities").hasRole("READ")
        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("pepe")
                .password("ronny")
                .roles("READ")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
