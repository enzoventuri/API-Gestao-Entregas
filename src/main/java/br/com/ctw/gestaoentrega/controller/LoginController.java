package br.com.ctw.gestaoentrega.controller;

import br.com.ctw.gestaoentrega.dto.LoginRequest;
import br.com.ctw.gestaoentrega.dto.LoginResponse;
import br.com.ctw.gestaoentrega.service.JwtService;
import br.com.ctw.gestaoentrega.service.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService detailsService;
    private final JwtService service;

    @PostMapping("/api/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = detailsService.loadUserByUsername(request.username());

        String token = service.generateToken(userDetails);

        return new LoginResponse(token);
    }

}
