package com.coimbra.vitor.finance.dto;

import com.coimbra.vitor.finance.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
		@NotBlank(message = "Login não pode estar em branco") @Email(message = "Login deve ser um e-mail válido") String login,

		@NotBlank(message = "Senha não pode estar em branco") @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String password,

		User.UserRole role) {
}
