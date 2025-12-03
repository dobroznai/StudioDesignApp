package de.ait.training.config;


import de.ait.training.service.SDUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(SDUserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authenticationProvider) throws Exception {

        http.authenticationProvider(authenticationProvider);

        http.csrf(csrf -> csrf.disable());

        http.headers(headers -> headers
                .frameOptions(frame -> frame.disable())
        );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/info/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/hours/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                // после успешного логина всегда редиректим сюда
                .defaultSuccessUrl("/api/public/info", true)
        );

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
