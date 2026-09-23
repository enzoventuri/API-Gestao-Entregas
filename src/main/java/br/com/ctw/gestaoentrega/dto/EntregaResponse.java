package br.com.ctw.gestaoentrega.dto;

public record EntregaResponse(
        Long id,
        Long idMotorista,
        String descricao,
        String status
) {
}
