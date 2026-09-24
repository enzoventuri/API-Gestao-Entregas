package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.MotoristaRequest;
import br.com.ctw.gestaoentrega.dto.MotoristaResponse;
import br.com.ctw.gestaoentrega.service.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para gerenciar endpoints de Motorista da aplicação
 */
@RestController
@RequestMapping("/api/motoristas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(
        name = "Motorista Controller",
        description = "Controller para gerenciar endpoints de Motorista"
)
public class MotoristaController {
    private final MotoristaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Retorna todos Motoristas",
            description = "Retorna Motoristas numa página"
    )
    @ApiResponse(responseCode = "200", description = "Lista de Motoristas")
    public Page<MotoristaResponse> getMotoristas(Pageable pageable) {
        return service.getMotoristas(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registra um Motorista",
            description = "Registra um Motorista com base em dados vindos do request"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motorista registrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe um Motorista com CNH igual ao enviado"
            )
    })
    public MotoristaResponse registerMotorista(@RequestBody MotoristaRequest request) {
        return service.registerMotorista(request);
    }

}
