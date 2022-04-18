package com.victoralmada.pedidos.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victoralmada.pedidos.dto.UserDto;
import com.victoralmada.pedidos.entities.User;
import com.victoralmada.pedidos.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<Page<UserDto>> findAll(@PageableDefault(page=0,size=10,sort="id",direction = Direction.ASC) Pageable pageable) {
		Page<UserDto> users = service.findAll(pageable);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable() Long id) {
		UserDto userDto = service.findById(id);
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping
	public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {	
		User user = service.insert(userDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri(); // Tivemos que criar para mostrar status 201, inserimos um novo valor!
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	@DeleteMapping(value="/{id}")
	public  ResponseEntity<Void> delete (@PathVariable() Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<UserDto> update(@PathVariable() Long id, @RequestBody()UserDto userDto) {
		User user = service.update(id, userDto);
		return ResponseEntity.ok().body(new UserDto(user));
	}
}
