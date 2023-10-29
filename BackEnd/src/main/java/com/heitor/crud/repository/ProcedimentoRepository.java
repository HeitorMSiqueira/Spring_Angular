package com.heitor.crud.repository;

import com.heitor.crud.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

   @Query( value ="select p.* from procedimentos p join clientes c on p.i_clientes = c.id" +
            " where upper( c.nome ) like upper (?1) and EXTRACT(MONTH FROM p.data_realizacao) = ?2",
            nativeQuery = true)
   List<Procedimento> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}