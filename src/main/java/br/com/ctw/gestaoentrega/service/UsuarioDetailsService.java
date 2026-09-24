package br.com.ctw.gestaoentrega.service;

import br.com.ctw.gestaoentrega.entity.Usuario;
import br.com.ctw.gestaoentrega.entity.UsuarioDetails;
import br.com.ctw.gestaoentrega.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service para retornar UserDetails com base no Username
 */
@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    /**
     * Retorna Informações do Usuário (exemplo: senha e autoridades) com base no username do Usuário
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException Exceção lançada quando o Usuário não é encontrado com o username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username));

        return new UsuarioDetails(usuario);
    }

}
