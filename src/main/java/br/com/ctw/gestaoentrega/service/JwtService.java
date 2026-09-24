package br.com.ctw.gestaoentrega.service;

import br.com.ctw.gestaoentrega.entity.UsuarioDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Service para gerenciamento do token JWT e algumas informações do Usuário
 */
@Service
public class JwtService {
    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.token.expiration}")
    private int tokenExpirationInMs;

    /**
     * Retorna uma chave para validar e assinar os tokens JWT
     *
     * <p>Representa a chave secreta do JWT, transformando em bytes para criptografia e cria uma chave criptográfica apropriada para algoritmos HMAC</p>
     * @return SecretKey
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Gera o token JWT
     * @param details Detalhes do Usuário
     * @return String (Token JWT)
     */
    public String generateToken(UserDetails details) {
        return Jwts.builder()
                .subject(details.getUsername())
                .claim("authorities", details.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpirationInMs))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Valida se o token JWT é valida com base no username e se o token já foi expirado
     * @param token Token JWT String
     * @param details Detalhes do Usuário (nesse caso somente o username é utilizado)
     * @return boolean
     */
    public boolean isTokenValid(String token, UserDetails details) {
        String username = extractUsername(token);

        return username.equals(details.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Valida se o token expirou, com base na data atual e a data de expiração do token JWT
     * @param token Token JWT String
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * Extrai o usuário do token JWT
     * @param token Token JWT String
     * @return Username do Usuário (String)
     */
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * Retorna dados do usuário dentro do Token JWT
     * @param token Token JWT String
     * @return Claims (Informações do Usuário dentro do Token JWT)
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
