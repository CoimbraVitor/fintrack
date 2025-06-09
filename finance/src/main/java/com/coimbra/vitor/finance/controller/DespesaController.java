package com.coimbra.vitor.finance.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coimbra.vitor.finance.dto.DespesaDTO;
import com.coimbra.vitor.finance.dto.DespesaRequestDTO;
import com.coimbra.vitor.finance.entities.Despesa;
import com.coimbra.vitor.finance.service.DespesaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("despesas")
public class DespesaController {

	@Autowired
	private DespesaService service;

	@PostMapping
	public ResponseEntity<Despesa> create(@RequestBody @Valid DespesaRequestDTO dto, Principal principal) {
		Despesa despesaCriada = service.create(dto, principal.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(despesaCriada);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Principal principal) {
		var despesas = service.findAll(principal.getName());
		return ResponseEntity.ok(despesas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDTO> findById(@PathVariable Integer id, Principal principal) {
		DespesaDTO despesa = service.findByIdAndUserLogin(id, principal.getName());
		return ResponseEntity.ok(despesa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DespesaDTO> update(@PathVariable Integer id, @RequestBody @Valid DespesaRequestDTO dto,
			Principal principal) {
		DespesaDTO updated = service.update(id, dto, principal.getName());
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id, Principal principal) {
		service.delete(id, principal.getName());
		return ResponseEntity.noContent().build();
	}
}
