package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ReceitaRequestDTO(
	    @NotBlank(message = "Nome é obrigatório")
	    @Size(max = 100, message = "Nome deve ter até 100 caracteres")
	    String nome,

	    @NotNull(message = "Valor é obrigatório")
	    @Positive(message = "Valor deve ser positivo")
	    Integer valorEmCents,

	    LocalDate dataRecebida
	) {}
