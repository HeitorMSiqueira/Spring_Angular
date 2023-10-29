package com.heitor.crud.controller;

import com.heitor.crud.model.Cliente;
import com.heitor.crud.repository.ClienteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){

        return clienteRepository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
        return clienteRepository.findById(id).map(clienteSave -> {
            clienteSave.setNome(cliente.getNome());
            clienteSave.setCpf(cliente.getCpf());
            Cliente updated = clienteRepository.save(clienteSave);
            return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> saveExcluido(@PathVariable Long id) {
        return clienteRepository.findById(id).map(clienteSave -> {
            clienteSave.setExcluido(Boolean.TRUE);
            Cliente updated = clienteRepository.save(clienteSave);
            return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") @NotNull Long id) {
        return clienteRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteRepository.findAll().stream()
                .filter(c -> c.getExcluido().equals(Boolean.FALSE))
                .collect(Collectors.toList());
    }
}