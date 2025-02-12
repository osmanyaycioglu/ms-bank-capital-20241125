package org.training.capital.microservice.mscustomeraggregator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSec(HttpSecurity httpSecurityParam) throws Exception {
        return httpSecurityParam.httpBasic(Customizer.withDefaults())
                                .formLogin(FormLoginConfigurer::disable)
                                .cors(CorsConfigurer::disable)
                                .csrf(CsrfConfigurer::disable)
                                .build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailService myUserDetailServiceParam){
        DaoAuthenticationProvider providerLoc = new DaoAuthenticationProvider();
        providerLoc.setPasswordEncoder(passwordEncoder());
        providerLoc.setUserDetailsService(myUserDetailServiceParam);
        return providerLoc;
    }

    @Bean
    public MyUserDetailService myUserDetailService(MySecurityConfig mySecurityConfigParam){
        System.out.println(mySecurityConfigParam);
        return new MyUserDetailService(mySecurityConfigParam.getUserInfos(), passwordEncoder());
    }
}
