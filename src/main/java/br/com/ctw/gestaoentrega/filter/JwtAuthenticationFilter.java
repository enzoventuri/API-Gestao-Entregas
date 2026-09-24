package br.com.ctw.gestaoentrega.filter;

import br.com.ctw.gestaoentrega.entity.UsuarioDetails;
import br.com.ctw.gestaoentrega.service.JwtService;
import br.com.ctw.gestaoentrega.service.UsuarioDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Validação da autenticação do usuário
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsuarioDetailsService detailsService;

    /**
     * Realiza a validação, se um Usuário consegue ser autenticado e autorizado para realizar uma ação
     * @param request Request do HTTP Servlet
     * @param response Response do HTTP Servlet
     * @param filterChain Todos os filtros
     * @throws ServletException Exceção lançada quando ocorre um erro durante o processamento de uma requisição de um Servlet
     * @throws IOException Exceção lançaca quando ocorre um erro na tentativa de uma operação de entrada ou saída
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails details = detailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, details)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    details, null, details.getAuthorities()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
