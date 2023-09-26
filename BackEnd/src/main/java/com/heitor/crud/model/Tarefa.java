package com.heitor.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "tarefas")

public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //Anotação de como vai ser apresentando o item de Objeto para Json e de Json para Objeto
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "status")
    private String status;

    @Column(name = "dh_criacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "dh_conclusao")
    private LocalDateTime dataHoraConclusao;

    @Column(name = "fl_excluido", nullable = false, columnDefinition = "boolean default false")
    private Boolean excluido = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        if(dataHoraCriacao == null){
            dataHoraCriacao = LocalDateTime.now();
        }
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraConclusao() {
        return dataHoraConclusao;
    }

    public void setDataHoraConclusao(LocalDateTime dataHoraConclusao) {
        this.dataHoraConclusao = dataHoraConclusao;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }
}
