package br.com.ctw.gestaoentrega.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request DTO de Motorista
 *
 * <p>Dados essenciais para a criação de um Motorista</p>
 * @param nome Nome do Motorista
 * @param cnh CNH do Motorista
 */
public record MotoristaRequest(
        @NotNull(message = "Motorista precisa ter nome!")
        String nome,

        @NotNull(message = "Motorista precisa ter CNH!")
        @Size(max = 11, message = "Ultrapassou o limite de carácteres do CNH!")
        String cnh
) {
}
