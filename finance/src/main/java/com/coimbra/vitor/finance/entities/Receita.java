package com.coimbra.vitor.finance.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity(name = "receita")
@Table(name = "receita")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	@NotNull(message = "Usuário é obrigatório")
	private User user;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Valor é obrigatório")
	@Positive(message = "Valor deve ser maior que zero")
	private Integer valorEmCents;

	private LocalDate dataRecebida;

	private LocalDate dataCriada;

	public Receita() {

		this.dataRecebida = LocalDate.now();
		this.dataCriada = LocalDate.now();
	}

	public Receita(User user, String nome, Integer valorEmCents, LocalDate dataRecebida, LocalDate dataCriada) {
		this.user = user;
		this.nome = nome;
		this.valorEmCents = valorEmCents;
		this.dataRecebida = dataRecebida != null ? dataRecebida : LocalDate.now();
		this.dataCriada = dataCriada != null ? dataCriada : LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getValorEmCents() {
		return valorEmCents;
	}
	
	public Integer getValor() {
		return valorEmCents/100;
	}

	public void setValorEmCents(Integer valorEmCents) {
		this.valorEmCents = valorEmCents;
	}

	public LocalDate getDataRecebida() {
		return dataRecebida;
	}

	public void setDataRecebida(LocalDate dataRecebida) {
		this.dataRecebida = (dataRecebida != null) ? dataRecebida : LocalDate.now();
	}

	public LocalDate getDataCriada() {
		return dataCriada;
	}

	public void setDataCriada(LocalDate dataCriada) {
		this.dataCriada = (dataCriada != null) ? dataCriada : LocalDate.now();
	}
}