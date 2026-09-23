package br.com.ctw.gestaoentrega.mapper;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.entity.Entrega;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntregaMapper {
    public EntregaResponse toResponse(Entrega entity) {
        return new EntregaResponse(
                entity.getId(),
                entity.getMotorista().getId(),
                entity.getDescricao(),
                entity.getStatus()
        );
    }

    public List<EntregaResponse> toResponsePage(List<Entrega> entregas) {
        return entregas.stream().map(this::toResponse).toList();
    }
}
