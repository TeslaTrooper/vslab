package de.hska.iwi.vslab.contentmanagementservice.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.iwi.vslab.contentmanagementservice.dto.ClientCategory;

@Component
public class CategoryClient {

	private String categoryUri = "http://category-service:8766/categories/";
	private final Map<Integer, ClientCategory> catCache = new LinkedHashMap<>();

	@Autowired
	private ProductClient productClient;

	@Autowired
	public CategoryClient() {
		
	}
	
	public ResponseEntity<Category> createCategory(String name) {
		RestTemplate rt = new RestTemplate();

		return rt.postForEntity(categoryUri, name, Category.class);
	}

	@HystrixCommand(fallbackMethod = "getAllCategoriesCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ClientCategory[] getCategories() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category[]> entity = rt.getForEntity(categoryUri, Category[].class);

		List<ClientCategory> cats = new ArrayList<>();

		for (Category c : entity.getBody()) {
			Product[] ps = productClient.getProductsByCategoryId(c.getId());
			cats.add(new ClientCategory(c.getId(), c.getName(), ps));
		}

		return cats.toArray(new ClientCategory[cats.size()]);
	}

	@HystrixCommand(fallbackMethod = "getCategoryCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ClientCategory getCategoryById(int id) {
		RestTemplate rt = new RestTemplate();
		Category c = rt.getForEntity(categoryUri + "/" + id, Category.class).getBody();

		return new ClientCategory(c.getId(), c.getName(), productClient.getProductsByCategoryId(c.getId()));
	}

	public boolean deleteCategory(int id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(categoryUri + "/" + id);

		return true;
	}

	public ClientCategory getCategoriesCache(int catId) {
		return catCache.get(catId);
	}

	public Collection<ClientCategory> getAllCategoriesCache() {
		return catCache.values();
	}

}
