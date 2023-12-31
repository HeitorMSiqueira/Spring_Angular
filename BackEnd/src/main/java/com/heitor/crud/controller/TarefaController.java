package com.heitor.crud.controller;

import com.heitor.crud.model.Tarefa;
import com.heitor.crud.repository.TarefaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> list() {
        return tarefaRepository.findAll().stream()
                .filter(t -> t.getExcluido().equals(Boolean.FALSE))
                .collect(Collectors.toList());
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<Tarefa> findById(@PathVariable("idTarefa") @NotNull Long id) {
        return tarefaRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tarefa create(@RequestBody @Valid Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable @NotNull Long id, @RequestBody @Valid Tarefa tarefa) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> saveExcluido(@PathVariable Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if(tarefaOptional.isPresent()){
            tarefaOptional.map(response -> {
                response.setExcluido(Boolean.TRUE);
                response.setDataHoraExclusao(LocalDateTime.now());
                return ResponseEntity.ok(this.tarefaRepository.save(response)).getBody();
            });
        }
       return ResponseEntity.noContent().build();
    }
}
