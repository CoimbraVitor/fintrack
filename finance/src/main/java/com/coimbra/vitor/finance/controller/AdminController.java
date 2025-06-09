package com.coimbra.vitor.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coimbra.vitor.finance.dto.DespesaDTO;
import com.coimbra.vitor.finance.dto.InvestimentoDTO;
import com.coimbra.vitor.finance.dto.ReceitaDTO;
import com.coimbra.vitor.finance.dto.ReceitaResponseDTO;
import com.coimbra.vitor.finance.dto.UserResponseDTO;
import com.coimbra.vitor.finance.service.DespesaService;
import com.coimbra.vitor.finance.service.InvestimentoService;
import com.coimbra.vitor.finance.service.ReceitaService;
import com.coimbra.vitor.finance.service.UserService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired private UserService userService;
	@Autowired private ReceitaService receitaService;
	@Autowired private DespesaService despesaService;
	@Autowired private InvestimentoService investimentoService;

	@GetMapping("/usuarios")
	public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/receitas")
	public ResponseEntity<List<ReceitaDTO>> listarTodasReceitas() {
		return ResponseEntity.ok(receitaService.findAllAdmin());
	}

	@GetMapping("/receitas/{login}")
	public ResponseEntity<List<ReceitaResponseDTO>> listarReceitasPorUsuario(@PathVariable String login) {
		return ResponseEntity.ok(receitaService.findAll(login));
	}

	@GetMapping("/despesas")
	public ResponseEntity<List<DespesaDTO>> listarTodasDespesas() {
		return ResponseEntity.ok(despesaService.findAllAdmin());
	}

	@GetMapping("/despesas/{login}")
	public ResponseEntity<List<DespesaDTO>> listarDespesasPorUsuario(@PathVariable String login) {
		return ResponseEntity.ok(despesaService.findAll(login));
	}

	@GetMapping("/investimentos")
	public ResponseEntity<List<InvestimentoDTO>> listarTodosInvestimentos() {
		return ResponseEntity.ok(investimentoService.findAllAdmin());
	}

	@GetMapping("/investimentos/{login}")
	public ResponseEntity<List<InvestimentoDTO>> listarInvestimentosPorUsuario(@PathVariable String login) {
		return ResponseEntity.ok(investimentoService.findAll(login));
	}
}

