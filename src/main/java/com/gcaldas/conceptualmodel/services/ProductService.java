package com.gcaldas.conceptualmodel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.domain.Product;
import com.gcaldas.conceptualmodel.repositories.CategoryRepository;
import com.gcaldas.conceptualmodel.repositories.ProductRepository;
import com.gcaldas.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository rep;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = rep.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return rep.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
