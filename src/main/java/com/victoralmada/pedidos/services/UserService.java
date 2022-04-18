package com.victoralmada.pedidos.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.victoralmada.pedidos.dto.UserDto;
import com.victoralmada.pedidos.entities.User;
import com.victoralmada.pedidos.repositories.UserRepository;
import com.victoralmada.pedidos.services.exceptions.DatabaseException;
import com.victoralmada.pedidos.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Page<UserDto> findAll(Pageable pageable) {
		Page<User> users = repository.findAll(pageable);
		return users.map(UserDto::new);
	}

	public UserDto findById(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException(id);
		}
		return new UserDto(user.get());
	}

	public User insert(UserDto userDto) {
		User user = new User();
		convertDate(user, userDto);
		user.setPassword(userDto.getPassword());
		repository.save(user);
		return user;
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, UserDto userDto) {
		try {
		User user = repository.findById(id).get();
		convertDate(user, userDto);
		repository.save(user);
		return user;
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void convertDate(User user, UserDto userDto) {
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());
	}
}
