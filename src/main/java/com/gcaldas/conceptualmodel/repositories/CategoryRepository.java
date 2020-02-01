package com.gcaldas.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcaldas.conceptualmodel.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
