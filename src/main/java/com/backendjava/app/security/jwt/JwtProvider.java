package com.backendjava.app.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(new Date().getTime() + expiration * 1000L))
                .sign(Algorithm.HMAC512(secret));
    }

    public String getUsernameOrEmail(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.decode(token).getSignature();
            return true;
        } catch (JWTDecodeException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
