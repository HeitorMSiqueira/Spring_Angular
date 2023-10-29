package com.heitor.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Data
@Entity
@Table(name= "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @NotNull(message = "Obrigatório informar nome")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "Obrigatório informar cpf")
    @Column(name = "cpf", nullable = false, length = 11)
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

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
