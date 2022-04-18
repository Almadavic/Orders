package com.victoralmada.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victoralmada.pedidos.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
