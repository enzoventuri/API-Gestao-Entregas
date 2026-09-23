package br.com.ctw.gestaoentrega.mapper;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.dto.MotoristaRequest;
import br.com.ctw.gestaoentrega.dto.MotoristaResponse;
import br.com.ctw.gestaoentrega.entity.Motorista;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MotoristaMapper {
    private final EntregaMapper entregaMapper;

    public Motorista toEntity(MotoristaRequest request) {
        return Motorista.builder()
                .nome(request.nome())
                .cnh(request.cnh())
                .entregas(List.of())
                .build();
    }

    public MotoristaResponse toResponse(Motorista entity) {
        return new MotoristaResponse(
                entity.getId(),
                entity.getNome(),
                entity.getCnh(),
                entregaMapper.toResponsePage(entity.getEntregas())
        );
    }

    public Page<MotoristaResponse> toResponsePage(Page<Motorista> motoristas) {
        return motoristas.map(this::toResponse);
    }

}
