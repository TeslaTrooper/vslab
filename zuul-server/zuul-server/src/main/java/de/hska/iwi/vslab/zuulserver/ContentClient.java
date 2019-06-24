package de.hska.iwi.vslab.zuulserver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.hska.iwi.vslab.zuulserver.dto.JSONProduct;

@Component
public class ContentClient {
	private String categoryUri = "http://content-management-service:8768/categories/";
	private String productUri = "http://content-management-service:8768/products/";
	private String userUri = "http://user-service:8762/users/";

	public ResponseEntity<Category> createCategory(final String name) {
		RestTemplate rt = new RestTemplate();
		
		return rt.postForEntity(categoryUri, name, Category.class);
	}

	public Category[] getCategories() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category[]> entity = rt.getForEntity(categoryUri, Category[].class);

		return entity.getBody();
	}

	public Category getCategoryById(int id) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category> entity = rt.getForEntity(categoryUri + id, Category.class);

		return entity.getBody();
	}

	public boolean deleteCategory(int id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(categoryUri + id);

		return true;
	}

	public ResponseEntity<Product> createProduct(JSONProduct p) {
		RestTemplate rt = new RestTemplate();
		
		return rt.postForEntity(productUri, p, Product.class);
	}

	public Product[] getProducts() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Product[]> entity = rt.getForEntity(productUri, Product[].class);

		return entity.getBody();
	}

	public Product[] getProductsByCategoryId(int catId) {
		return null;
	}

	public Product getProductById(int id) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Product> entity = rt.getForEntity(productUri + id, Product.class);

		return entity.getBody();
	}

	public boolean deleteProduct(int id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(productUri + id);

		return true;
	}

	public Product[] searchProduct(String searchText) {
		return null;
	}

	public User createUser(Registration r) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<User> user = rt.postForEntity(userUri, r, User.class);

		return user.getBody();
	}

	public User loginUser(User u) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<User> user = rt.postForEntity(userUri + "login", u, User.class);

		return user.getBody();
	}

	public User logoutUser(User u) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<User> user = rt.postForEntity(userUri + "logout", u, User.class);

		return user.getBody();
	}
}
