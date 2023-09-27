package com.heitor.crud.model;

import com.heitor.crud.controller.TarefaController;
import com.heitor.crud.repository.TarefaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNull;

@ExtendWith(MockitoExtension.class)
class TarefaTest {

    @InjectMocks
    TarefaController tarefaController;

    @Mock
    TarefaRepository repository;

    Tarefa tarefa;
    @BeforeEach
    public void setup() {
        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("titulo");
        tarefa.setDescricao("Descricao");
        tarefa.setExcluido(Boolean.FALSE);
        tarefa.setStatus("PENDENTE");
        tarefa.setDataHoraCriacao(LocalDateTime.of(2023,9,27,18,56, 0));
        tarefa.setDataHoraConclusao(LocalDateTime.now());
        tarefa.setDataHoraExclusao(LocalDateTime.now());
    }

    @Test
    public void validaSeCampoTituloEstaNull() {
        Tarefa tarefaMock = mock(Tarefa.class);
        when(tarefaMock.getTitulo()).thenReturn(null);
        assertNull("valida titulo null", tarefaMock.getTitulo());
    }

    @Test
    public void validaSeCampoTituloNaoEstaNull() {
        Tarefa tarefaMock = mock(Tarefa.class);
        when(tarefaMock.getTitulo()).thenReturn(tarefa.getTitulo());
        assertNotNull(tarefaMock.getTitulo());
    }

    @Test
    public void validaSeCampoDataHoraCriacaoNaoEstaNull() {
        Tarefa tarefaMock = mock(Tarefa.class);
        when(tarefaMock.getDataHoraCriacao()).thenReturn(tarefa.getDataHoraCriacao());
        assertNotNull(tarefaMock.getDataHoraCriacao());
    }

    @Test
    public void validaSeCampoDataHoraCriacaoEstaNull() {
        Tarefa tarefaMock = mock(Tarefa.class);
        when(tarefaMock.getDataHoraCriacao()).thenReturn(null);
        assertNull("data criação null", tarefaMock.getDataHoraCriacao());
    }
}