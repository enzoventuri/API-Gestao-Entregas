package br.com.ctw.gestaoentrega.mapper;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.entity.Entrega;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper de Entrega
 *
 * <p>Realiza transformações de objetos somente para Response</p>
 */
@Component
public class EntregaMapper {
    /**
     * Transforma uma Request em Entity
     * @param entity Entidade de Entrega
     * @return Entrega Response DTO
     */
    public EntregaResponse toResponse(Entrega entity) {
        return new EntregaResponse(
                entity.getId(),
                entity.getMotorista().getId(),
                entity.getDescricao(),
                entity.getStatus()
        );
    }

    /**
     * Utiliza uma Lista de Entrega e transforma numa Lista de Entrega Response DTOs
     * @param entregas Lista de Entrega
     * @return Lista de Entrega Response DTO
     */
    public List<EntregaResponse> toResponsePage(List<Entrega> entregas) {
        return entregas.stream().map(this::toResponse).toList();
    }
}
