package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.LoginRequest;
import br.com.ctw.gestaoentrega.dto.LoginResponse;
import br.com.ctw.gestaoentrega.service.JwtService;
import br.com.ctw.gestaoentrega.service.UsuarioDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para gerenciar autenticação do usuário
 */
@RestController
@RequiredArgsConstructor
@Tag(
        name = "Login Controller",
        description = "Controller para gerenciar endpoint de login"
)
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService detailsService;
    private final JwtService service;

    /**
     * Faz login do usuário com base em informações necessárias para realizar o login
     * @param request Request vindo do usuário
     * @return LoginResponse
     */
    @PostMapping("/api/auth/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Realiza login",
            description = "Tentativa de realizar login do usuário com base em informações de autenticação"
    )
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = detailsService.loadUserByUsername(request.username());

        String token = service.generateToken(userDetails);

        return new LoginResponse(token);
    }

}
