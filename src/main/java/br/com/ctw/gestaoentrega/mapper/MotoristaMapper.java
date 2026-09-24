package br.com.ctw.gestaoentrega.mapper;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.dto.MotoristaRequest;
import br.com.ctw.gestaoentrega.dto.MotoristaResponse;
import br.com.ctw.gestaoentrega.entity.Motorista;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper de Motorista
 *
 * <p>Realiza transformações de objetos, somente para Entidade e Response</p>
 */
@Component
@RequiredArgsConstructor
public class MotoristaMapper {
    private final EntregaMapper entregaMapper;

    /**
     * Transforma uma Request em Entity
     * @param request Dados da Request do Motorista
     * @return Motorista Entity
     */
    public Motorista toEntity(MotoristaRequest request) {
        return Motorista.builder()
                .nome(request.nome())
                .cnh(request.cnh())
                .entregas(List.of())
                .build();
    }

    /**
     * Transforma Entitdade Motorista em Response DTO
     * @param entity Entidade Motorista
     * @return Motorista Response DTO
     */
    public MotoristaResponse toResponse(Motorista entity) {
        return new MotoristaResponse(
                entity.getId(),
                entity.getNome(),
                entity.getCnh(),
                entregaMapper.toResponsePage(entity.getEntregas())
        );
    }

    /**
     * Utiliza uma Página de Motoristas e transforma numa Página de Motorista Response DTOs
     * @param motoristas Página de Motorista Entidades
     * @return Página de Motorista Response DTOs
     */
    public Page<MotoristaResponse> toResponsePage(Page<Motorista> motoristas) {
        return motoristas.map(this::toResponse);
    }

}
