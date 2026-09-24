package br.com.ctw.gestaoentrega.dto;

/**
 * Dados para realizar login
 * @param username Username do Usuário
 * @param password Senha do Usuário
 */
public record LoginRequest(
        String username,
        String password
) {
}
