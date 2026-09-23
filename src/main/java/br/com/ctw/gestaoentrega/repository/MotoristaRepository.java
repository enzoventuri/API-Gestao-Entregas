package br.com.ctw.gestaoentrega.repository;

import br.com.ctw.gestaoentrega.entity.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    @Override
    Page<Motorista> findAll(Pageable pageable);

    boolean existsByCnh(String cnh);
}
