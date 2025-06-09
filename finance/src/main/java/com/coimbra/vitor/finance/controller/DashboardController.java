package com.coimbra.vitor.finance.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coimbra.vitor.finance.service.DashboardService;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/saldo")
    public ResponseEntity<Map<String, Integer>> getSaldo(Principal principal) {
        Integer saldo = dashboardService.calcularSaldo(principal.getName());
        return ResponseEntity.ok(Map.of("saldo", saldo));
    }

    @GetMapping("/resumo")
    public ResponseEntity<Map<String, Integer>> getResumo(Principal principal) {
        return ResponseEntity.ok(dashboardService.obterResumo(principal.getName()));
    }


}
