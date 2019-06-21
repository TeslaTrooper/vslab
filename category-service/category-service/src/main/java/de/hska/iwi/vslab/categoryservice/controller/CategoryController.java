package de.hska.iwi.vslab.categoryservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.categoryservice.dao.Category;
import de.hska.iwi.vslab.categoryservice.dao.CategoryRepo;

@RestController

public class CategoryController {

	@Autowired
	private CategoryRepo repo;

	@PostMapping(value = "/categories")
	public ResponseEntity<Category> create(@RequestBody String name) throws Exception {
		// Create Category
		if (name.length() == 0) {
			throw new IllegalArgumentException("Category must be given a name!");
		}

		Category c = repo.save(new Category(name));
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}

	@GetMapping(value = "/categories")
	public ResponseEntity<Category[]> getCategories() {
		Iterable<Category> allCategories = repo.findAll();

		return new ResponseEntity<Category[]>(toArray(allCategories), HttpStatus.OK);

	}

	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable final long id) {
		Category category = repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@DeleteMapping(value = "/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable final long id) {
		repo.deleteById(id);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	private Category[] toArray(Iterable<Category> cats) {
		List<Category> list = new ArrayList<>();

		for (Category c : cats) {
			list.add(c);
		}

		return list.toArray(new Category[list.size()]);
	}

}
