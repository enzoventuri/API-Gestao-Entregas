package br.com.ctw.gestaoentrega.dto;

import java.time.LocalDateTime;

/**
 * Response DTO para Erros que podem ocorrer durante a execução da aplicação
 * @param error Mensagem de Erro da exceção
 * @param status Código de Status HTTP
 * @param path URI de onde ocorreu o erro
 * @param timestamp Tempo exato que ocorreu o erro
 */
public record ErrorResponse(
        String error,
        Integer status,
        String path,
        LocalDateTime timestamp
) {
}
