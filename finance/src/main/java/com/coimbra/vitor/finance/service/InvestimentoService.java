package com.coimbra.vitor.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coimbra.vitor.finance.dto.InvestimentoDTO;
import com.coimbra.vitor.finance.dto.InvestimentoRequestDTO;
import com.coimbra.vitor.finance.entities.Investimento;
import com.coimbra.vitor.finance.entities.User;
import com.coimbra.vitor.finance.repositories.InvestimentoRepository;
import com.coimbra.vitor.finance.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<InvestimentoDTO> findAll(String login) {
        return repository.findByUserEmail(login)
                .stream()
                .map(this::toDTO)
                .toList();
    }
    
    public List<InvestimentoDTO> findAllAdmin() {
        return repository.findAll().stream()
            .map(this::toDTO)
            .toList();
    }


    public Investimento create(InvestimentoRequestDTO dto, String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Investimento investimento = new Investimento(
            user,
            dto.nome(),
            dto.tipo(),
            dto.valorAplicadoEmCents(),
            dto.dataAplicacao(),
            dto.percentualEstimadoRetorno(),
            dto.dataResgate()
        );

        return repository.save(investimento);
    }

    public InvestimentoDTO findByIdAndUserLogin(Integer id, String login) {
        Investimento investimento = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Investimento com ID " + id + " não encontrado"));

        return toDTO(investimento);
    }

    public InvestimentoDTO update(Integer id, InvestimentoRequestDTO dto, String login) {
        Investimento investimento = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Investimento com ID " + id + " não encontrado"));

        investimento.setNome(dto.nome());
        investimento.setTipo(dto.tipo());
        investimento.setValorAplicadoEmCents(dto.valorAplicadoEmCents());
        investimento.setDataAplicacao(dto.dataAplicacao());
        investimento.setPercentualEstimadoRetorno(dto.percentualEstimadoRetorno());
        investimento.setDataResgate(dto.dataResgate());

        return toDTO(repository.save(investimento));
    }

    public void delete(Integer id, String login) {
        Investimento investimento = repository.findByIdAndUserEmail(id, login)
                .orElseThrow(() -> new EntityNotFoundException("Investimento com ID " + id + " não encontrado"));

        repository.delete(investimento);
    }

    private InvestimentoDTO toDTO(Investimento investimento) {
        return new InvestimentoDTO(
            investimento.getId(),
            investimento.getNome(),
            investimento.getTipo(),
            investimento.getValorAplicadoEmCents(),
            investimento.getDataAplicacao(),
            investimento.getPercentualEstimadoRetorno(),
            investimento.getDataResgate()
        );
    }
}
