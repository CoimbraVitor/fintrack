package com.coimbra.vitor.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coimbra.vitor.finance.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
	List<Despesa> findByUserEmail(String email);

	Optional<Despesa> findByIdAndUserEmail(Integer id, String email);

}
