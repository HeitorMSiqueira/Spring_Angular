package com.heitor.crud.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcedimentoDto {

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.valor.obrigatorio}")
    private String valor;

    @NotEmpty(message = "{campo.dataRealizacao.obrigatorio}")
    private String dataRealizacao;

    private String dataCadastro;
    private Long idCliente;
}
