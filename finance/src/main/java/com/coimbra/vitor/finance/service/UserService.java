package com.coimbra.vitor.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coimbra.vitor.finance.dto.UserResponseDTO;
import com.coimbra.vitor.finance.repositories.UserRepository;
import com.coimbra.vitor.finance.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl {

	@Autowired
	UserRepository repository;

	public List<UserResponseDTO> findAll() {
		List<UserResponseDTO> users = repository.findAll().stream()
				.map(user -> new UserResponseDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCountry(), user.getUserName())).toList();
		return users;
	}
}
