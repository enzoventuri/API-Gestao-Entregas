package br.com.ctw.gestaoentrega.service;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.entity.Entrega;
import br.com.ctw.gestaoentrega.exceptions.NotFoundException;
import br.com.ctw.gestaoentrega.mapper.EntregaMapper;
import br.com.ctw.gestaoentrega.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregaService {
    private final EntregaRepository repository;
    private final EntregaMapper entregaMapper;

    public EntregaResponse getEntregaById(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não foi encontrada com ID: " + id));

        return entregaMapper.toResponse(entrega);
    }

    public void deleteEntregaById(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não foi encontrada com ID: " + id));

        repository.delete(entrega);
    }

}
