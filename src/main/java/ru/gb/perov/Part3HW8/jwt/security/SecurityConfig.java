package ru.gb.perov.Part3HW8.jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig /* extends WebSecurityConfigAdapter */ {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().requestMatchers("/auth/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(JwtFilter filter, HttpSecurity security) throws Exception {
        return security.authorizeHttpRequests()
                .requestMatchers("/api/**")
                .authenticated()
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("pass")
                .authorities("ADMIN", "MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... providers) {
        return new ProviderManager(providers);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new StandardAuthenticationProvider();
    }

//    @Bean
//    public JwtFilter jwtFilter() {
//        return new JwtFilter();
//    }
}
