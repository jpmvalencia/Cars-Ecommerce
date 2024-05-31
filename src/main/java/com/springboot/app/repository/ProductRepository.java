package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}


