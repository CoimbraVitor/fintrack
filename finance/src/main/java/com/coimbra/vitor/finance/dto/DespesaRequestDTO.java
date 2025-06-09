package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DespesaRequestDTO(
    @NotBlank(message = "Nome é obrigatório") String nome,
    @NotNull(message = "Valor é obrigatório") @Positive(message = "Valor deve ser maior que zero") Integer valorEmCents,
    LocalDate dataGasto,
    LocalDate dataCriada
) {}
