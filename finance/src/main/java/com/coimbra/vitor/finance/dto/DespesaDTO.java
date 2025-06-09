package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

public record DespesaDTO(
    Integer id,
    String nome,
    Integer valorEmCents,
    LocalDate dataGasto
) {}
