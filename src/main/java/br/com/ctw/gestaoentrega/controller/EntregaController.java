package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.EntregaResponse;
import br.com.ctw.gestaoentrega.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para gerenciar endpoints de Entrega da aplicação
 *
 * <p>Disponibiliza endpoints de consulta por id e remoção</p>
 */
@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(
        name = "Entrega Controller",
        description = "Controller para gerenciar endpoints de Entrega"
)
public class EntregaController {
    private final EntregaService service;

    /**
     * Retorna uma Entrega com base no identificador único
     * @param id Identificador único de uma entidade Entrega
     * @return EntregaResponse
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Buscar Entrega por ID",
            description = "Retorna dados da Entrega com base no identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorna dados da Entrega com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entrega não foi encontrada com o identificador único enviado"
            )
    })
    public EntregaResponse getEntregaById(@PathVariable Long id) {
        return service.getEntregaById(id);
    }

    /**
     * Deleta uma Entrega com base no identificador único
     * @param id Identificador único de uma entidade Entrega
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Deleta Entrega por ID",
            description = "Deleta Entrega com base no identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Entrega deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entrega não encontrada"
            )
    })
    public void deleteEntregaById(@PathVariable Long id) {
        service.deleteEntregaById(id);
    }

}
