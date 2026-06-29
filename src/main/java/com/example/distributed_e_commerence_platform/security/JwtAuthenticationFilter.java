package com.example.distributed_e_commerence_platform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    public JwtAuthenticationFilter(
            SecretKey secretKey) {

        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorization =
                request.getHeader("Authorization");

        if (authorization != null &&
                authorization.startsWith("Bearer ")) {

            String token =
                    authorization.substring(7);

            try {

                Claims claims =
                        Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();

                Long userId =
                        claims.get("userId",
                                Long.class);

                String role =
                        claims.get("role",
                                String.class);

                UsernamePasswordAuthenticationToken
                        authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                List.of(
                                        new SimpleGrantedAuthority(
                                                "ROLE_" + role
                                        )
                                )
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                authentication
                        );

            } catch (Exception ex) {

                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}