package de.hska.iwi.vslab.contentmanagementservice.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.contentmanagementservice.dto.ClientCategory;

@RestController
@RequestMapping(value = "/categories/")
public class CategoryController {

	private final Map<Integer, ClientCategory> catCache;

	@Autowired
	private CategoryClient categoryClient;
	
	@Autowired
	private ProductClient productClient;

	@Autowired
	public CategoryController() {
		//this.categoryClient = categoryClient;
		catCache = new LinkedHashMap<>();
	}

	@PostMapping(consumes = "text/plain")
	public ResponseEntity<Category> createCategory(@RequestBody String name) {
		// TODO add new category
		return categoryClient.createCategory(name);
	}

	@GetMapping
	public ClientCategory[] getCategory() {
		ClientCategory[] cs = categoryClient.getCategories();

		// Login user
		// TODO return category
		if (cs != null)
			for (ClientCategory c : cs)
				catCache.putIfAbsent(c.getId(), c);

		return cs;
	}

	@GetMapping("{id}")
	public ClientCategory getCategoryById(@PathVariable int id) {
		ClientCategory c = categoryClient.getCategoryById(id);

		if (c != null) {
			catCache.putIfAbsent(id, c);
			return c;
		}

		return null;
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteCategoryById(@PathVariable int id) {
		boolean deleted = categoryClient.deleteCategory(id);

		if (deleted) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@GetMapping("{id}/products")
	public Product[] getProducts(@PathVariable int id) {
		return productClient.getProductsByCategoryId(id);
	}

}
