package com.heitor.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "tarefas")

public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @Length(max = 100)
    @NotBlank(message = "Obrigatório informar título")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Length(max = 200)
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private String status;

    @Column(name = "dh_criacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "dh_conclusao")
    private LocalDateTime dataHoraConclusao;

    @Column(name = "dh_exclusao")
    private LocalDateTime dataHoraExclusao;

    @Column(name = "fl_excluido", nullable = false)
    private Boolean excluido = Boolean.FALSE;
}
