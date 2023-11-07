package com.fiap.cp3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Produto {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;

    private String categoria;

    private BigDecimal preco;

    private LocalDate dataCadastro;

}
