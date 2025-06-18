package com.coimbra.vitor.finance.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coimbra.vitor.finance.entities.Despesa;
import com.coimbra.vitor.finance.entities.Investimento;
import com.coimbra.vitor.finance.entities.Receita;
import com.coimbra.vitor.finance.repositories.DespesaRepository;
import com.coimbra.vitor.finance.repositories.InvestimentoRepository;
import com.coimbra.vitor.finance.repositories.ReceitaRepository;

@Service
public class DashboardService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private InvestimentoRepository investimentoRepository;

	public Integer calcularSaldo(String email) {
		int totalReceitas = receitaRepository.findByUserEmail(email).stream().mapToInt(Receita::getValorEmCents).sum();

		int totalDespesas = despesaRepository.findByUserEmail(email).stream().mapToInt(Despesa::getValorEmCents).sum();

		return totalReceitas - totalDespesas;
	}

	public Map<String, Integer> obterResumo(String email) {
		int totalReceitas = receitaRepository.findByUserEmail(email).stream().mapToInt(Receita::getValorEmCents).sum();

		int totalDespesas = despesaRepository.findByUserEmail(email).stream().mapToInt(Despesa::getValorEmCents).sum();

		int totalInvestimentos = investimentoRepository.findByUserEmail(email).stream()
				.mapToInt(Investimento::getValorAplicadoEmCents).sum();

		return Map.of("receitas", totalReceitas, "despesas", totalDespesas, "investimentos", totalInvestimentos);
	}



}
