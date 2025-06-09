package com.coimbra.vitor.finance.dto;

import java.time.LocalDate;

public record InvestimentoDTO(
	    Integer id,
	    String nome,
	    String tipo,
	    Integer valorAplicadoEmCents,
	    LocalDate dataAplicacao,
	    Double percentualEstimadoRetorno,
	    LocalDate dataResgate
	) {}
