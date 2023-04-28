package ru.gb.perov.Part3HW8.jwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.gb.perov.Part3HW8.jwt.service.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Configuration
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (Objects.nonNull(authorizationHeaderValue) && authorizationHeaderValue.startsWith("Bearer ")) {
                String bearerTokenValue = authorizationHeaderValue.substring(7);

                String username = jwtService.getUsername(bearerTokenValue);
                List<? extends GrantedAuthority> authorities = jwtService.getAuthorities(bearerTokenValue);

                if (Objects.nonNull(username)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(username, null, authorities)
                    );
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
