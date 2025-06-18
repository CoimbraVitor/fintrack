package com.coimbra.vitor.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coimbra.vitor.finance.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
	List<Receita> findByUserEmail(String email);

	Optional<Receita> findByIdAndUserEmail(Integer id, String email);

}
