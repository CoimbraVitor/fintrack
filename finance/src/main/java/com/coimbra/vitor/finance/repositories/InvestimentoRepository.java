package com.coimbra.vitor.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coimbra.vitor.finance.entities.Investimento;

public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {
    List<Investimento> findByUserLogin(String login);
    Optional<Investimento> findByIdAndUserLogin(Integer id, String login);
}
