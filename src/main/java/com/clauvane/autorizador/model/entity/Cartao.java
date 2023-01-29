package com.clauvane.autorizador.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cartao", uniqueConstraints = @UniqueConstraint(columnNames = {"numeroCartao"}))
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 16)
    private String numeroCartao;

    @Column(nullable = false, length = 20)
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldo;

}
