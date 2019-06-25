package de.hska.iwi.vslab.zuulserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.hska.iwi.vslab.zuulserver.dto.Category;
import de.hska.iwi.vslab.zuulserver.dto.ClientCategory;
import de.hska.iwi.vslab.zuulserver.dto.JSONProduct;
import de.hska.iwi.vslab.zuulserver.dto.Product;
import de.hska.iwi.vslab.zuulserver.dto.Registration;
import de.hska.iwi.vslab.zuulserver.dto.User;

@Component
public class ContentClient {

	private String categoryUri = "http://content-management-service:8768/categories/";
	private String productUri = "http://content-management-service:8768/products/";
	private String userUri = "http://user-service:8762/users/";

	private final RestTemplate rt;

	public ContentClient() {
		rt = new RestTemplate();
	}

	public ResponseEntity<Category> createCategory(final String name) {
		return rt.postForEntity(categoryUri, name, Category.class);
	}

	public ClientCategory[] getCategories() {
		ResponseEntity<Category[]> entity = rt.getForEntity(categoryUri, Category[].class);
		
		List<ClientCategory> cats = new ArrayList<>();
		
		for(Category c : entity.getBody()) {
			Product[] ps = getProductsByCategoryId(c.getId());
			cats.add(new ClientCategory(c.getId(), c.getName(), ps));
		}
		
		return cats.toArray(new ClientCategory[cats.size()]);
	}

	public ClientCategory getCategoryById(int id) {
		ResponseEntity<Category> entity = rt.getForEntity(categoryUri + id, Category.class);
		Category c = entity.getBody();

		return new ClientCategory(c.getId(), c.getName(), getProductsByCategoryId(c.getId()));
	}

	public boolean deleteCategory(int id) {
		rt.delete(categoryUri + id);

		return true;
	}

	public ResponseEntity<Product> createProduct(JSONProduct p) {
		return rt.postForEntity(productUri, p, Product.class);
	}

	public Product[] getProducts() {
		ResponseEntity<Product[]> entity = rt.getForEntity(productUri, Product[].class);

		return entity.getBody();
	}

	public Product[] getProductsByCategoryId(int catId) {
		Product[] products = getProducts();
		List<Product> results = new ArrayList<>();
		for(Product p : products) {
			if(p.getCategoryId() == catId) {
				results.add(p);
			}
		}
		Product[] array = results.toArray(new Product[results.size()]);
		return array;
	}

	public Product getProductById(int id) {
		ResponseEntity<Product> entity = rt.getForEntity(productUri + id, Product.class);

		return entity.getBody();
	}

	public boolean deleteProduct(int id) {
		rt.delete(productUri + id);

		return true;
	}

	public Product[] searchProduct(String searchText) {
		return null;
	}

	public User createUser(Registration r) {
		ResponseEntity<User> user = rt.postForEntity(userUri, r, User.class);

		return user.getBody();
	}

	public User loginUser(User u) {
		ResponseEntity<User> user = rt.postForEntity(userUri + "login", u, User.class);

		return user.getBody();
	}

	public User logoutUser(User u) {
		ResponseEntity<User> user = rt.postForEntity(userUri + "logout", u, User.class);

		return user.getBody();
	}

	public Product[] getProductsForSearchValue(String searchDescription, double searchMinPrice, double searchMaxPrice) {
		return rt.getForEntity(productUri + "search?searchDescription=" + searchDescription + "&searchMinPrice="
				+ searchMinPrice + "&searchMaxPrice=" + searchMaxPrice, Product[].class).getBody();
	}
}
