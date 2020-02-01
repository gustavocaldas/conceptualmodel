package com.gcaldas.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcaldas.conceptualmodel.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
