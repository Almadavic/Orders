package com.victoralmada.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victoralmada.pedidos.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
