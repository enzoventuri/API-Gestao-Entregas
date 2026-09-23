package br.com.ctw.gestaoentrega.repository;

import br.com.ctw.gestaoentrega.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username) throws UsernameNotFoundException;
}
