package org.training.capital.microservice.mscustomeraggregator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSec(HttpSecurity httpSecurityParam) throws Exception {
        return httpSecurityParam.httpBasic(HttpBasicConfigurer::disable)
                                .formLogin(FormLoginConfigurer::disable)
                                .cors(CorsConfigurer::disable)
                                .csrf(CsrfConfigurer::disable)
                                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/**",
                                                                              "/security/**")
                                                             .anonymous()
                                                             .requestMatchers("/api/v1/customer/provision/**")
                                                             .hasAnyAuthority("ADMIN","USER")
                                                             .anyRequest()
                                                             .authenticated())
                                .addFilterBefore(jwtFilter(),
                                                 UsernamePasswordAuthenticationFilter.class)
                                .sessionManagement(SessionManagementConfigurer::disable)
                                .build();

    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailService myUserDetailServiceParam) {
        DaoAuthenticationProvider providerLoc = new DaoAuthenticationProvider();
        providerLoc.setPasswordEncoder(passwordEncoder());
        providerLoc.setUserDetailsService(myUserDetailServiceParam);
        return providerLoc;
    }

    @Bean
    public MyUserDetailService myUserDetailService(MySecurityConfig mySecurityConfigParam) {
        System.out.println(mySecurityConfigParam);
        return new MyUserDetailService(mySecurityConfigParam.getUserInfos(),
                                       passwordEncoder());
    }

    @Bean
    public JWTService jwtService() {
        return new JWTService();
    }
}
