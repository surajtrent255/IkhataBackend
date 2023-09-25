package com.ishanitech.iaccountingrest.config.auth;

import com.ishanitech.iaccountingrest.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/resource/**", "/home").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/userconfig/**").permitAll()
                .requestMatchers("/api/v1/branch/**").permitAll()
                .requestMatchers("api/v1/payment/**").permitAll()
                .requestMatchers("/api/v1/cheque/**").permitAll()
                .requestMatchers("/api/v1/expense/**").permitAll()
                .requestMatchers("/api/v1/fixedAssets/**").permitAll()
                .requestMatchers("/api/v1/receipt/**").permitAll()
                .requestMatchers("/api/v1/company/**").permitAll()
                .requestMatchers("/api/v1/bank/**").permitAll()
                .requestMatchers("/user/company/**").permitAll()
                .requestMatchers("/product").permitAll()
                .requestMatchers("/product/**").permitAll()
                .requestMatchers("/vatRateType").permitAll()
                .requestMatchers("/category").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }


}
