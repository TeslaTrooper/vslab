package de.hska.iwi.vslab.contentmanagementservice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.contentmanagementservice.clients.CategoryClient;
import de.hska.iwi.vslab.contentmanagementservice.clients.ProductClient;

@RestController
@RequestMapping(value = "/categories/")
public class CategoryController {

	private final Map<Integer, Category> catCache = new LinkedHashMap<Integer, Category>();
	private CategoryClient categoryClient = new CategoryClient();
	private ProductClient productClient = new ProductClient();

	@PostMapping(consumes = "text/plain")
	public ResponseEntity<Category> createCategory(@RequestBody String name) {
		// TODO add new category
		return categoryClient.createCategory(name);
	}

	@GetMapping
	public ResponseEntity<Category[]> getCategory() {
		ResponseEntity<Category[]> cs = categoryClient.getCategories();

		// Login user
		// TODO return category
		if (cs != null)
			for (Category c : cs.getBody())
				catCache.putIfAbsent(c.getId(), c);

		return cs;
	}

	@GetMapping("{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
		// Logout user

		Category c = categoryClient.getCategoryById(id).getBody();

		if (c != null) {
			catCache.putIfAbsent(id, c);
			return new ResponseEntity<>(c, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

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
	public ResponseEntity<Product[]> getProducts(@PathVariable int id) {
		// Logout user
		return productClient.getProductsByCategoryId(id);
	}

}
