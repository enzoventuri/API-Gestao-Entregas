package br.com.ctw.gestaoentrega.repository;

import br.com.ctw.gestaoentrega.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Repositório de Usuário
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Procura Usuário com base no username
     * @param username Username do Usuário
     * @return Optional de Usuário
     * @throws UsernameNotFoundException Exceção de quando Usuário não é encontrado
     */
    Optional<Usuario> findByUsername(String username) throws UsernameNotFoundException;
}
