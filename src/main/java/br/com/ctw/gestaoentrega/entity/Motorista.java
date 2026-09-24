package br.com.ctw.gestaoentrega.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entidade de Motorista
 */
@Entity
@Table(name = "tb_motorista")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "nome",
            length = 100,
            nullable = false
    )
    private String nome;

    @Column(
            name = "cnh",
            length = 20,
            unique = true,
            nullable = false
    )
    private String cnh;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    private List<Entrega> entregas;

}
