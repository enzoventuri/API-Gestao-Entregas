package br.com.ctw.gestaoentrega.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_entrega")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "descricao",
            nullable = false
    )
    private String descricao;

    @Column(
            name = "status",
            length = 30,
            nullable = false
    )
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
}
