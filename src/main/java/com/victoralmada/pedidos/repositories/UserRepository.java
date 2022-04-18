package com.victoralmada.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victoralmada.pedidos.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
