package br.com.ctw.gestaoentrega.service;

import br.com.ctw.gestaoentrega.dto.MotoristaRequest;
import br.com.ctw.gestaoentrega.dto.MotoristaResponse;
import br.com.ctw.gestaoentrega.entity.Motorista;
import br.com.ctw.gestaoentrega.exceptions.AlreadyExistsMotoristaException;
import br.com.ctw.gestaoentrega.mapper.MotoristaMapper;
import br.com.ctw.gestaoentrega.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service de Motorista para validar regras de negócio
 */
@Service
@RequiredArgsConstructor
public class MotoristaService {
    private final MotoristaRepository repository;
    private final MotoristaMapper mapper;

    public Page<MotoristaResponse> getMotoristas(Pageable pageable) {
        return mapper.toResponsePage(repository.findAll(pageable));
    }

    public MotoristaResponse registerMotorista(MotoristaRequest request) {
        if (repository.existsByCnh(request.cnh())) {
            throw new AlreadyExistsMotoristaException("Motorista já foi cadastrado!");
        }

        Motorista motorista = mapper.toEntity(request);

        Motorista saved = repository.save(motorista);

        return mapper.toResponse(saved);
    }

}
