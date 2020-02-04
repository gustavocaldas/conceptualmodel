package com.gcaldas.conceptualmodel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.repositories.CategoryRepository;
import com.gcaldas.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository rep;

	public Category find(Integer id) {
		Optional<Category> obj = rep.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return rep.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return rep.save(obj);
	}
	
	
}
