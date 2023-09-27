package com.heitor.crud.controller;

import com.heitor.crud.model.Tarefa;
import com.heitor.crud.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaControllerTest {

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
        tarefa.setDataHoraCriacao(LocalDateTime.now());
    }

    @Test
    public void deveBuscarCarregarListaTarefa(){
        when(repository.findAll()).thenReturn(Collections.singletonList(tarefa));
        List<Tarefa> tarefaList = tarefaController.list();
        assertEquals(Collections.singletonList(tarefa), tarefaList);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}