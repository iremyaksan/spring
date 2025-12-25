package com.ornek.diyet.guvenlik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class GuvenlikAyarları {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/giris", "/login",
                                "/hasta/kayit", "/hasta/kaydet",
                                "/diyetisyen/kayit", "/diyetisyen/kaydet").permitAll()
                        .requestMatchers("/diyetisyen/**").hasRole("DIYETISYEN")
                        .requestMatchers("/hasta/**").hasRole("HASTA")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/giris")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/panel", true)
                        .failureUrl("/giris?hata=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/cikis")
                        .logoutSuccessUrl("/giris?cikis=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
