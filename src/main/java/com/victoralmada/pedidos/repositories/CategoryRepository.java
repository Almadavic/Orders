package com.victoralmada.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victoralmada.pedidos.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
