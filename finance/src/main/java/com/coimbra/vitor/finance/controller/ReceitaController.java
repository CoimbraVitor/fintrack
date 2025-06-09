package com.coimbra.vitor.finance.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coimbra.vitor.finance.dto.ReceitaDTO;
import com.coimbra.vitor.finance.dto.ReceitaRequestDTO;
import com.coimbra.vitor.finance.entities.Receita;
import com.coimbra.vitor.finance.service.ReceitaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("receitas")
public class ReceitaController {

	@Autowired
	ReceitaService service;

	@GetMapping
	public ResponseEntity<?> findAll(Principal principal) {
		var receitasDto = service.findAll(principal.getName());
		return ResponseEntity.ok(receitasDto);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ReceitaRequestDTO dto, Principal principal) {
		Receita receitaCriada = service.create(dto, principal.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(receitaCriada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id, Principal principal) {
		ReceitaDTO receita = service.findByIdAndUserLogin(id, principal.getName());
		return ResponseEntity.ok(receita);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReceitaDTO> update(@PathVariable Integer id, @RequestBody @Valid ReceitaRequestDTO dto,
			Principal principal) {
		ReceitaDTO updated = service.update(id, dto, principal.getName());
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id, Principal principal) {
		service.delete(id, principal.getName());
		return ResponseEntity.noContent().build();
	}
}
