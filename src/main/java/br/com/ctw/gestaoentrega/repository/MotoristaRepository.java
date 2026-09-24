package br.com.ctw.gestaoentrega.repository;

import br.com.ctw.gestaoentrega.entity.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Motorista
 */
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    /**
     * Procura por todos Motoristas e coloca numa página
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *          {@literal null}.
     * @return Página de Motoristas
     */
    @Override
    Page<Motorista> findAll(Pageable pageable);

    /**
     * Retorna verdadeiro ou falso se existe um Motorista com o CNH recebido
     * @param cnh CNH do Motorista
     * @return boolean
     */
    boolean existsByCnh(String cnh);
}
