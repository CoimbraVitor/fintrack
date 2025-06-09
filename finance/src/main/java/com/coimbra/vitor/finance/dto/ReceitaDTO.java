package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

import com.coimbra.vitor.finance.entities.Receita;

public record ReceitaDTO(Integer id, String nome, Integer valorEmCents, LocalDate dataRecebida) {

    public ReceitaDTO(Receita receita) {
        this(
            receita.getId(),
            receita.getNome(),
            receita.getValorEmCents(),
            receita.getDataRecebida()
        );
    }
}