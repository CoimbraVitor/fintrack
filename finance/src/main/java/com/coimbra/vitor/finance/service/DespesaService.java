package com.coimbra.vitor.finance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coimbra.vitor.finance.dto.DespesaDTO;
import com.coimbra.vitor.finance.dto.DespesaRequestDTO;
import com.coimbra.vitor.finance.entities.Despesa;
import com.coimbra.vitor.finance.entities.User;
import com.coimbra.vitor.finance.repositories.DespesaRepository;
import com.coimbra.vitor.finance.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<DespesaDTO> findAll(String login) {
        return repository.findByUserEmail(login)
                .stream()
                .map(this::toDTO)
                .toList();
    }
    
    public List<DespesaDTO> findAllAdmin() {
        return repository.findAll().stream()
            .map(this::toDTO)
            .toList();
    }


    public Despesa create(DespesaRequestDTO dto, String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Despesa despesa = new Despesa(
            user,
            dto.nome(),
            dto.valorEmCents(),
            dto.dataGasto() != null ? dto.dataGasto() : LocalDate.now(),
            dto.dataCriada() != null ? dto.dataCriada() : LocalDate.now()
        );

        return repository.save(despesa);
    }

    public DespesaDTO findByIdAndUserLogin(Integer id, String login) {
        Despesa despesa = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Despesa com ID " + id + " não encontrada"));

        return toDTO(despesa);
    }

    public DespesaDTO update(Integer id, DespesaRequestDTO dto, String login) {
        Despesa despesa = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Despesa com ID " + id + " não encontrada"));

        despesa.setNome(dto.nome());
        despesa.setValorEmCents(dto.valorEmCents());
        despesa.setDataGasto(dto.dataGasto() != null ? dto.dataGasto() : LocalDate.now());
        despesa.setDataCriada(dto.dataCriada() != null ? dto.dataCriada() : LocalDate.now());

        return toDTO(repository.save(despesa));
    }

    public void delete(Integer id, String login) {
        Despesa despesa = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Despesa com ID " + id + " não encontrada"));

        repository.delete(despesa);
    }

    private DespesaDTO toDTO(Despesa despesa) {
        return new DespesaDTO(
            despesa.getId(),
            despesa.getNome(),
            despesa.getValorEmCents(),
            despesa.getDataGasto()
        );
    }
}
