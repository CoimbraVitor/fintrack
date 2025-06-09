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

@Entity(name = "despesa")
@Table(name = "despesa")
public class Despesa {

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

    private LocalDate dataGasto;

    private LocalDate dataCriada;

    public Despesa() {
        this.dataGasto = LocalDate.now();
        this.dataCriada = LocalDate.now();
    }

    public Despesa(User user, String nome, Integer valorEmCents, LocalDate dataGasto, LocalDate dataCriada) {
        this.user = user;
        this.nome = nome;
        this.valorEmCents = valorEmCents;
        this.dataGasto = dataGasto != null ? dataGasto : LocalDate.now();
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

    public LocalDate getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(LocalDate dataGasto) {
        this.dataGasto = dataGasto != null ? dataGasto : LocalDate.now();
    }

    public LocalDate getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(LocalDate dataCriada) {
        this.dataCriada = dataCriada != null ? dataCriada : LocalDate.now();
    }
}
