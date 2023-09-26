package com.heitor.crud.repository;

import com.heitor.crud.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//informa que vai fazer o acesso ao BD
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    //JpaRepository faz a conexao com o banco, as consultas find, findAll, etc...
}
