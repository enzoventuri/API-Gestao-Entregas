package br.com.ctw.gestaoentrega.dto;

import java.util.List;

/**
 * Response DTO do Motorista
 *
 * <p>Retorna dados para visualização do Motorista</p>
 * @param id ID do Motorista
 * @param nome Nome do Motorista
 * @param cnh CNH do Motorista
 * @param entregas Entregas do Motorista
 */
public record MotoristaResponse(
        Long id,
        String nome,
        String cnh,
        List<EntregaResponse> entregas
) {
}
