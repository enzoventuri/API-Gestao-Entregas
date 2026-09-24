package br.com.ctw.gestaoentrega.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade do Usuário
 */
@Entity
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "username",
            length = 50,
            unique = true,
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "role",
            length = 20,
            nullable = false
    )
    private String role;

}
