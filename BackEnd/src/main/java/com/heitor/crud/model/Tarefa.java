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
    @Column(name = "titulo")
    private String titulo;

    @Length(max = 200)
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Obrigatório informar status")
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "dh_criacao")
    private LocalDateTime dataHoraCriacao;

    @Column(name = "dh_conclusao")
    private LocalDateTime dataHoraConclusao;

    @Column(name = "dh_exclusao")
    private LocalDateTime dataHoraExclusao;

    @NotNull
    @Column(name = "fl_excluido")
    private Boolean excluido = Boolean.FALSE;
}
