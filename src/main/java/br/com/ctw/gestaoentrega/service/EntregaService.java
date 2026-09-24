package br.com.ctw.gestaoentrega.service;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.entity.Entrega;
import br.com.ctw.gestaoentrega.exceptions.NotFoundException;
import br.com.ctw.gestaoentrega.mapper.EntregaMapper;
import br.com.ctw.gestaoentrega.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service de Entrega para validar regras de negócio
 */
@Service
@RequiredArgsConstructor
public class EntregaService {
    private final EntregaRepository repository;
    private final EntregaMapper entregaMapper;

    /**
     * Procura por uma Entrega com base no ID
     *
     * <p>Valida se ela realmente existe com o ID recebido</p>
     * @param id ID da Entrega
     * @throws NotFoundException Exceção lançada quando Entrega não é encontrada
     * @return Entrega Response DTO
     */
    public EntregaResponse getEntregaById(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não foi encontrada com ID: " + id));

        return entregaMapper.toResponse(entrega);
    }

    /**
     * Deleta Entrega com base no ID
     *
     * <p>Valida se a Entrega existe com base no ID recebido</p>
     * @param id ID da Entrega para ser deletado
     * @throws NotFoundException Exceção lançada quando Entrega não é encontrada
     */
    public void deleteEntregaById(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não foi encontrada com ID: " + id));

        repository.delete(entrega);
    }

}
