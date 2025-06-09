package com.coimbra.vitor.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coimbra.vitor.finance.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
	List<Despesa> findByUserLogin(String login);

	Optional<Despesa> findByIdAndUserLogin(Integer id, String login);

}
