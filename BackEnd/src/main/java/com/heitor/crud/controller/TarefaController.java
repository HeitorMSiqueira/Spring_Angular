package com.heitor.crud.controller;

import com.heitor.crud.model.Tarefa;
import com.heitor.crud.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired // injecao da dependencia
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> list() {
        return tarefaRepository.findAll();
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<Tarefa> findById(@PathVariable("idTarefa") Long id) {
        return tarefaRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tarefa create(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaRepository.findById(id)
                .map(response -> {
                    response.setTitulo(tarefa.getTitulo());
                    response.setDescricao(tarefa.getDescricao());
                    response.setDataHoraConclusao(tarefa.getDataHoraConclusao());
                    response.setStatus(tarefa.getStatus());
                    Tarefa updated = tarefaRepository.save(response);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETE lógico
    @PutMapping("delete/{id}")
    public ResponseEntity<Tarefa> saveExcluido(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaRepository.findById(id)
                .map(response -> {
                    response.setExcluido(Boolean.TRUE);
                    response.setDataHoraConclusao(LocalDateTime.now());
                    response.setStatus(tarefa.getStatus());
                    Tarefa deleted = tarefaRepository.save(response);
                    return ResponseEntity.ok().body(deleted);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    //Delete do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(recordFound -> {
                    tarefaRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
