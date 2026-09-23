package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.MotoristaRequest;
import br.com.ctw.gestaoentrega.dto.MotoristaResponse;
import br.com.ctw.gestaoentrega.service.MotoristaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motoristas")
@RequiredArgsConstructor
public class MotoristaController {
    private final MotoristaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MotoristaResponse> getMotoristas(Pageable pageable) {
        return service.getMotoristas(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MotoristaResponse registerMotorista(@RequestBody MotoristaRequest request) {
        return service.registerMotorista(request);
    }

}
