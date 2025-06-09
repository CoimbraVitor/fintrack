package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InvestimentoRequestDTO(
	    @NotBlank(message = "Nome é obrigatório") String nome,
	    @NotBlank(message = "Tipo é obrigatório") String tipo,
	    @NotNull(message = "Valor aplicado é obrigatório") @Positive Integer valorAplicadoEmCents,
	    @NotNull(message = "Data da aplicação é obrigatória") LocalDate dataAplicacao,
	    @NotNull(message = "Percentual estimado de retorno é obrigatório") @DecimalMin(value = "0.0", inclusive = false) Double percentualEstimadoRetorno,
	    LocalDate dataResgate 
	) {}
