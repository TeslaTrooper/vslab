package de.hska.iwi.vslab.contentmanagementservice.clients;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.iwi.vslab.contentmanagementservice.Category;

public class CategoryClient {

	private String categoryUri = "http://category-service:8766/categories/";
	private final Map<Integer, Category> catCache = new LinkedHashMap<Integer, Category>();

	public ResponseEntity<Category> createCategory(String name) {
		RestTemplate rt = new RestTemplate();
		
		return rt.postForEntity(categoryUri, name, Category.class);
	}

	@HystrixCommand(fallbackMethod = "getAllCategoriesCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ResponseEntity<Category[]> getCategories() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category[]> entity = rt.getForEntity(categoryUri, Category[].class);

		return entity;
	}

	@HystrixCommand(fallbackMethod = "getCategoryCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ResponseEntity<Category> getCategoryById(int id) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category> entity = rt.getForEntity(categoryUri + "/" + id, Category.class);

		return entity;
	}

	public boolean deleteCategory(int id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(categoryUri + "/" + id);

		return true;
	}
	
	public Response getCategoriesCache(int catId) {
		return Response.ok(catCache.get(catId)).build();
	}

	public Response getAllCategoriesCache() {
		return Response.ok(catCache.values()).build();
	}

}
