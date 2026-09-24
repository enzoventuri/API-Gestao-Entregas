package br.com.ctw.gestaoentrega.dto;

/**
 * Response DTO de Entrega
 * @param id ID da Entrega
 * @param idMotorista ID do Motorista responsável pela Entrega
 * @param descricao Descrição da Entrega
 * @param status Status da Entrega
 */
public record EntregaResponse(
        Long id,
        Long idMotorista,
        String descricao,
        String status
) {
}
