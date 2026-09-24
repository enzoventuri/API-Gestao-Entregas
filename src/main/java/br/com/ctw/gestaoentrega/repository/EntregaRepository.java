package br.com.ctw.gestaoentrega.repository;

import br.com.ctw.gestaoentrega.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Entrega
 */
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
