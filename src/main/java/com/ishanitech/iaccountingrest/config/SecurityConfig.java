package com.ishanitech.iaccountingrest.config;// package com.ishanitech.iaccountingrest.config;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.HttpMethod;
 import
 org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import
 org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
 import
 org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import
 org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 import org.springframework.web.cors.CorsConfiguration;
 import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 import org.springframework.web.filter.CorsFilter;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.ishanitech.iaccountingrest.security.CustomAuthenticationEntryPoint;
 import com.ishanitech.iaccountingrest.security.CustomAuthenticationProvider;
 import com.ishanitech.iaccountingrest.security.TokenAuthenticationFilter;
 import com.ishanitech.iaccountingrest.security.TokenAuthorizationFilter;
 import com.ishanitech.iaccountingrest.utils.JsonTokenHelper;

 /**
 * Custom Security configuration class.
 * Any customization (except auth providers) related to security has to be
 done
 * in this class.
 *
 * @author <b> Umesh Bhujel
 * @since 1.0
 */
 @Configuration
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
 public class SecurityConfig {
     private CustomAuthenticationProvider authenticationProvider;
     private CustomAuthenticationEntryPoint authenticationEntryPoint;
     private ObjectMapper objectMapper;
     private JsonTokenHelper tokenHelper;
     private final TokenAuthorizationFilter tokenAuthorizationFilter;

     public SecurityConfig(CustomAuthenticationProvider authenticationProvider,
     CustomAuthenticationEntryPoint authenticationEntryPoint,
     ObjectMapper objectMapper,
     JsonTokenHelper tokenHelper,
     TokenAuthorizationFilter tokenAuthorizationFilter
     ) {

         this.authenticationProvider = authenticationProvider;
         this.authenticationEntryPoint = authenticationEntryPoint;
         this.objectMapper = objectMapper;
         this.tokenHelper = tokenHelper;
         this.tokenAuthorizationFilter = tokenAuthorizationFilter;
     }


//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception
//     {
//        auth.authenticationProvider(authenticationProvider);
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.cors()
//         .and()
//         .csrf().disable()
//         .httpBasic().disable()
//         .exceptionHandling()
//         .authenticationEntryPoint(authenticationEntryPoint)
//         .and()
//         .authorizeRequests()
//         .antMatchers("/resource/**", "/home").permitAll()
//         .anyRequest()
//         .authenticated()
//         .and()
//         .addFilter(corsConfiguration())
//         .addFilter(new TokenAuthenticationFilter(authenticationManager(),
//         objectMapper, tokenHelper))
//         .addFilterAfter(new TokenAuthorizationFilter(tokenHelper, objectMapper),
//         TokenAuthenticationFilter.class)
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .enableSessionUrlRewriting(false);
//     }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                 .cors()
         .and()
         .csrf().disable()
         .httpBasic().disable()
         .exceptionHandling()
         .authenticationEntryPoint(authenticationEntryPoint)
         .and()
         .authorizeRequests()
         .requestMatchers("/resource/**", "/home").permitAll()
         .requestMatchers("/student/**").permitAll()
                 .requestMatchers("/login").permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .addFilter(corsConfiguration())
                 .authenticationProvider(authenticationProvider)
         .addFilter(new TokenAuthenticationFilter(authenticationManager, objectMapper, tokenHelper))
                 .addFilterBefore(tokenAuthorizationFilter, TokenAuthenticationFilter.class)
         .addFilterAfter(new TokenAuthorizationFilter(tokenHelper, objectMapper),
         TokenAuthenticationFilter.class)
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .enableSessionUrlRewriting(false);

         return httpSecurity.build();

        }

//     javatechie
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/students/").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/products/**")
//                .authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
////                .addFilterBefore(tokenAuthorizationFilter, TokenAuthenticationFilter.class)
//                .build();
//    }

//     endjavatechie

     /**
     * @return CorsFilter object. Configuration to allow cors request..
     */

     @Bean
     CorsFilter corsConfiguration() {
     UrlBasedCorsConfigurationSource urlBasedCors = new
     UrlBasedCorsConfigurationSource();
     CorsConfiguration corsConfig = new CorsConfiguration();
     corsConfig.setAllowCredentials(true);
     corsConfig.addAllowedOrigin("*");
     corsConfig.addAllowedHeader("*");
     corsConfig.addAllowedMethod("OPTIONS");
     corsConfig.addAllowedMethod("GET");
     corsConfig.addAllowedMethod("POST");
     corsConfig.addAllowedMethod("PUT");
     corsConfig.addAllowedMethod("DELETE");
     urlBasedCors.registerCorsConfiguration("/**", corsConfig);
     return new CorsFilter(urlBasedCors);
     }
 }

// @Configuration
// @EnableWebSecurity
//// @RequiredArgsConstructor
// public class SecurityConfiguration {
//
//     private final JwtAuthenticationFilter jwtAuthFilter;
//     private final AuthenticationProvider authenticationProvider;
//     private final LogoutHandler logoutHandler;
//
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf()
//                 .disable()
//                 .authorizeHttpRequests()
//                 .requestMatchers("/api/v1/auth/**")
//                 .permitAll()
//                 .anyRequest()
//                 .authenticated()
//                 .and()
//                 .sessionManagement()
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authenticationProvider(authenticationProvider)
//                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                 .logout()
//                 .logoutUrl("/api/v1/auth/logout")
//                 .addLogoutHandler(logoutHandler)
//                 .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
//
//         return http.build();
//     }
// }

















