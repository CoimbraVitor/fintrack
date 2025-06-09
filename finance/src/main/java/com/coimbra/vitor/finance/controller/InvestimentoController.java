package com.coimbra.vitor.finance.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coimbra.vitor.finance.dto.InvestimentoDTO;
import com.coimbra.vitor.finance.dto.InvestimentoRequestDTO;
import com.coimbra.vitor.finance.entities.Investimento;
import com.coimbra.vitor.finance.service.InvestimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {

    @Autowired
    private InvestimentoService service;

    @GetMapping
    public ResponseEntity<List<InvestimentoDTO>> findAll(Principal principal) {
        List<InvestimentoDTO> investimentos = service.findAll(principal.getName());
        return ResponseEntity.ok(investimentos);
    }

    @PostMapping
    public ResponseEntity<Investimento> create(@RequestBody @Valid InvestimentoRequestDTO dto, Principal principal) {
        Investimento investimentoCriado = service.create(dto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(investimentoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoDTO> findById(@PathVariable Integer id, Principal principal) {
        InvestimentoDTO investimento = service.findByIdAndUserLogin(id, principal.getName());
        return ResponseEntity.ok(investimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentoDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid InvestimentoRequestDTO dto,
            Principal principal) {
        InvestimentoDTO investimentoAtualizado = service.update(id, dto, principal.getName());
        return ResponseEntity.ok(investimentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, Principal principal) {
        service.delete(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
