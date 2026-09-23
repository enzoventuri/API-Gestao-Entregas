package br.com.ctw.gestaoentrega.dto;

import java.util.List;

public record MotoristaResponse(
        Long id,
        String nome,
        String cnh,
        List<EntregaResponse> entregas
) {
}
