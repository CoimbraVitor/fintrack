package com.coimbra.vitor.finance.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "investimento")
public class Investimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Tipo é obrigatório")
	private String tipo;

	@NotNull(message = "Valor aplicado é obrigatório")
	@Positive(message = "Valor deve ser maior que zero")
	private Integer valorAplicadoEmCents;

	@NotNull(message = "Data da aplicação é obrigatória")
	private LocalDate dataAplicacao;

	@NotNull(message = "Percentual estimado de retorno é obrigatório")
	@DecimalMin(value = "0.0", inclusive = false, message = "Percentual deve ser maior que zero")
	private Double percentualEstimadoRetorno;

	private LocalDate dataResgate; 

	public Investimento() {
	}

	public Investimento(User user, String nome, String tipo, Integer valorAplicadoEmCents, LocalDate dataAplicacao,
			Double percentualEstimadoRetorno, LocalDate dataResgate) {
		this.user = user;
		this.nome = nome;
		this.tipo = tipo;
		this.valorAplicadoEmCents = valorAplicadoEmCents;
		this.dataAplicacao = dataAplicacao;
		this.percentualEstimadoRetorno = percentualEstimadoRetorno;
		this.dataResgate = dataResgate;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getValorAplicadoEmCents() {
		return valorAplicadoEmCents;
	}

	public void setValorAplicadoEmCents(Integer valorAplicadoEmCents) {
		this.valorAplicadoEmCents = valorAplicadoEmCents;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public Double getPercentualEstimadoRetorno() {
		return percentualEstimadoRetorno;
	}

	public void setPercentualEstimadoRetorno(Double percentualEstimadoRetorno) {
		this.percentualEstimadoRetorno = percentualEstimadoRetorno;
	}

	public LocalDate getDataResgate() {
		return dataResgate;
	}

	public void setDataResgate(LocalDate dataResgate) {
		this.dataResgate = dataResgate;
	}

}
