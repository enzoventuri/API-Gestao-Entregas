package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {
    private final EntregaService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntregaResponse getEntregaById(@PathVariable Long id) {
        return service.getEntregaById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntregaById(@PathVariable Long id) {
        service.deleteEntregaById(id);
    }

}
