package com.forohub.security;


import com.forohub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret-key}")
    private String secret;



    public String generateToken(Usuario usuario) {
        try {
            var algoritmo = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (com.auth0.jwt.exceptions.JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.require(algoritmo)
                    .withIssuer("ForoHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (com.auth0.jwt.exceptions.JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

}
