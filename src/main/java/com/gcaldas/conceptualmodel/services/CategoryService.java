package com.gcaldas.conceptualmodel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository rep;
	
	public Category find(Integer id) {
		Optional<Category> obj = rep.findById(id);
		
		return obj.orElse(null); 
	}
}
