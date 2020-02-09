package com.gcaldas.conceptualmodel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.repositories.CategoryRepository;
import com.gcaldas.conceptualmodel.services.exceptions.DataIntegrityException;
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
	
	public void delete(Integer id) {
		find(id);
		try {
			rep.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to exclude a category that has products.");
		}
	}	
	
	public List<Category> findAll() {
		return rep.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
	}
}
