package com.coimbra.vitor.finance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
		@NotBlank(message = "Email não pode estar em branco") @Email(message = "Email deve ser um e-mail válido") String email,
		@NotBlank(message = "Senha não pode estar em branco") @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String password,
		@NotBlank(message = "Nome não pode estar em branco") String firstName,
		@NotBlank(message = "Sobrenome não pode estar em branco") String lastName,
		@NotBlank(message = "País não pode estar em branco") String country,
		@NotBlank(message = "Nome de usuário não pode estar em branco") String userName) {
}
