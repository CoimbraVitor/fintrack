package com.coimbra.vitor.finance.service.impl;

import java.util.List;

import com.coimbra.vitor.finance.dto.UserResponseDTO;

public interface UserServiceImpl {
		List<UserResponseDTO> findAll();
		
}
