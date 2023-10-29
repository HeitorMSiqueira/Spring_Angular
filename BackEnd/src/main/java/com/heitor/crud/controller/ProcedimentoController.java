package com.heitor.crud.controller;

import com.heitor.crud.dto.ProcedimentoDto;
import com.heitor.crud.model.Cliente;
import com.heitor.crud.model.Procedimento;
import com.heitor.crud.repository.ClienteRepository;
import com.heitor.crud.repository.ProcedimentoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Procedimento salvar(@RequestBody @Valid ProcedimentoDto procedimentoDto){
        LocalDate dataRealizacao = LocalDate.parse(procedimentoDto.getDataRealizacao(), DateTimeFormatter.ofPattern("dd/M/yyyy"));
        Long clienteId = procedimentoDto.getIdCliente();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente inexistente"));

        Procedimento procedimento = new Procedimento();
        procedimento.setDescricao(procedimentoDto.getDescricao());
        procedimento.setDataRealizacao(dataRealizacao);
        procedimento.setCliente(cliente);
        procedimento.setValor(converterPreco(procedimentoDto.getValor()));
        return procedimentoRepository.save(procedimento);
    }

    @GetMapping
    public List<Procedimento> pesquisar(
            @RequestParam(value="nome", required=false, defaultValue = "") String nome,
            @RequestParam(value="mes", required=false) Integer mes){
        return procedimentoRepository.findByNomeClienteAndMes("%"+nome+"%", mes);
    }

//    @GetMapping
//    public List<ServicoPrestado> pesquisar(
//            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
//            @RequestParam(value = "mes", required = false) Integer mes){
//
//        return repository.findByNomeClienteAndMes("%"+ nome +"%", mes);
//    }

    private BigDecimal converterPreco(String preco){
        if(preco != null) {
            String precoFormatado = preco.replace(".", "").replace(",", ".");
            return new BigDecimal(precoFormatado);
        }
        return null;
    }

//    @PutMapping("{id}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public ResponseEntity<Procedimento> atualizar(@PathVariable Long id, @RequestBody @Valid Procedimento procedimento){
//        return procedimentoRepository.findById(id).map(procedimentoSave -> {
//            procedimentoSave.setDescricao(procedimento.getDescricao());
//            procedimentoSave.setDataRealizacao(dataRealizacao);
//            procedimentoSave.setValor(converterPreco(procedimento.getValor()));
//
//            Cliente updated = procedimentoRepository.save(clienteSave);
//            return ResponseEntity.ok().body(updated);
//            }).orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Procedimento> saveExcluido(@PathVariable Long id) {
        return procedimentoRepository.findById(id).map(procedimentoSave -> {
            procedimentoSave.setExcluido(Boolean.TRUE);
            Procedimento updated = procedimentoRepository.save(procedimentoSave);
            return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Procedimento> findById(@PathVariable("id") @NotNull Long id) {
        return procedimentoRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping
//    public List<Procedimento> listAllProcedimentos(){
//        return procedimentoRepository.findAll().stream()
//                .filter(c -> c.getExcluido().equals(Boolean.FALSE))
//                .collect(Collectors.toList());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Tarefa> update(@PathVariable @NotNull Long id, @RequestBody @Valid Tarefa tarefa) {
//        return tarefaRepository.findById(id)
//                .map(response -> {
//                    response.setTitulo(tarefa.getTitulo());
//                    response.setDescricao(tarefa.getDescricao());
//                    response.setDataHoraConclusao(tarefa.getDataHoraConclusao());
//                    response.setStatus(tarefa.getStatus());
//                    Tarefa updated = tarefaRepository.save(response);
//                    return ResponseEntity.ok().body(updated);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
}
