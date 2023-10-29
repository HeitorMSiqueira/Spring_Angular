package com.heitor.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "procedimentos")
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @NotNull(message = "Obrigatório informar descrição")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "i_clientes")
    private Cliente cliente;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name= "data_realizacao", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRealizacao;

    @Column(name= "data_cadastro", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "fl_excluido")
    private Boolean excluido;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
        setExcluido(Boolean.FALSE);
    }
}
