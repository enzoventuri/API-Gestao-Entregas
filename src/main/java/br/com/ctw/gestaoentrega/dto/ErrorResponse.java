package br.com.ctw.gestaoentrega.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        Integer status,
        String path,
        LocalDateTime timestamp
) {
}
