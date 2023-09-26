package com.heitor.crud.service;

import com.heitor.crud.model.Tarefa;
import com.heitor.crud.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TarefaService {

//    @Autowired // injecao da dependencia
//    private TarefaRepository tarefaRepository;


//    public ResponseEntity<Tarefa> saveExcluido(Long id) {
//        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
//        if(tarefaOptional.isPresent()){
//            tarefaOptional.map(response -> {
//                response.setExcluido(Boolean.TRUE);
//                response.setDataHoraExclusao(LocalDateTime.now());
//                return this.tarefaRepository.save(response);
//            });
//        }
//        return null;
//    }
}
