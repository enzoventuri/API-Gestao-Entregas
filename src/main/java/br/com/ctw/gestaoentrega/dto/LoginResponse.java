package br.com.ctw.gestaoentrega.dto;

/**
 * Response DTO de Login
 *
 * <p>Retorna um token JWT caso o login foi realizado com sucesso</p>
 * @param token Token JWT gerado
 */
public record LoginResponse(
        String token
) {
}
