package com.coimbra.vitor.finance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coimbra.vitor.finance.dto.ReceitaDTO;
import com.coimbra.vitor.finance.dto.ReceitaRequestDTO;
import com.coimbra.vitor.finance.dto.ReceitaResponseDTO;
import com.coimbra.vitor.finance.entities.Receita;
import com.coimbra.vitor.finance.entities.User;
import com.coimbra.vitor.finance.repositories.ReceitaRepository;
import com.coimbra.vitor.finance.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaService {
	@Autowired
	private ReceitaRepository repository;
	@Autowired
	private UserRepository userRepository;

	public List<ReceitaResponseDTO> findAll(String login) {
		List<Receita> receitas = repository.findByUserLogin(login);
		return receitas.stream().map(r -> new ReceitaResponseDTO(r.getValor(), r.getNome())).toList();
	}
	
	
	public List<ReceitaDTO> findAllAdmin() {
	    return repository.findAll().stream()
	        .map(this::toDTO)
	        .toList();
	}


	public Receita create(ReceitaRequestDTO dto, String login) {
		User user = userRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		Receita receita = new Receita();
		receita.setUser(user);
		receita.setNome(dto.nome());
		receita.setValorEmCents(dto.valorEmCents());
		receita.setDataRecebida(dto.dataRecebida() != null ? dto.dataRecebida() : LocalDate.now());
		receita.setDataCriada(LocalDate.now());

		return repository.save(receita);
	}

	public ReceitaDTO findByIdAndUserLogin(Integer id, String email) {
		Receita receita = repository.findByIdAndUserLogin(id, email)
				.orElseThrow(() -> new EntityNotFoundException("Receita com ID " + id + " não encontrada"));

		return new ReceitaDTO(receita);

	}

	@Transactional
	public ReceitaDTO update(Integer id, ReceitaRequestDTO dto, String login) {
		Receita receita = repository.findByIdAndUserLogin(id, login)
				.orElseThrow(() -> new EntityNotFoundException("Receita com ID " + id + " não encontrada"));

		receita.setNome(dto.nome());
		receita.setValorEmCents(dto.valorEmCents());
		receita.setDataRecebida(dto.dataRecebida() != null ? dto.dataRecebida() : LocalDate.now());

		return new ReceitaDTO(receita);
	}

	@Transactional
	public void delete(Integer id, String login) {
		Receita receita = repository.findByIdAndUserLogin(id, login)
				.orElseThrow(() -> new EntityNotFoundException("Receita com ID " + id + " não encontrada"));

		repository.delete(receita);
	}
	
	private ReceitaDTO toDTO(Receita receita) {
	    return new ReceitaDTO(
	        receita.getId(),
	        receita.getNome(),
	        receita.getValorEmCents(),
	        receita.getDataRecebida()
	    );
	}


}
