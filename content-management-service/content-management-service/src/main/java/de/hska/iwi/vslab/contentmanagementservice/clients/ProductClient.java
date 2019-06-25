package de.hska.iwi.vslab.contentmanagementservice.clients;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.iwi.vslab.contentmanagementservice.Product;
import de.hska.iwi.vslab.contentmanagementservice.dto.JSONProduct;

public class ProductClient {

	private String productUri = "http://product-service:8764/products/";
	private final Map<Integer, Product> productCache = new LinkedHashMap<Integer, Product>();

	private final RestTemplate rt;

	public ProductClient() {
		rt = new RestTemplate();
	}

	public ResponseEntity<Product> createProduct(JSONProduct p) {
		return rt.postForEntity(productUri, p, Product.class);
	}

	@HystrixCommand(fallbackMethod = "getAllProductsCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ResponseEntity<Product[]> getProducts() {
		ResponseEntity<Product[]> entity = rt.getForEntity(productUri, Product[].class);

		return entity;
	}

	public ResponseEntity<Product[]> getProductsByCategoryId(int catId) {
		return null;
	}

	@HystrixCommand(fallbackMethod = "getProductCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public ResponseEntity<Product> getProductById(int id) {
		ResponseEntity<Product> entity = rt.getForEntity(productUri + "/" + id, Product.class);

		if (entity != null && entity.getBody() != null)
			productCache.putIfAbsent(id, entity.getBody());

		return entity;
	}

	public boolean deleteProduct(int id) {
		rt.delete(productUri + "/" + id);

		return true;
	}

	public Response getProductCache(int catId) {
		return Response.ok(productCache.get(catId)).build();
	}

	public Response getAllProductsCache() {
		return Response.ok(productCache.values()).build();
	}

	public ResponseEntity<Product[]> getProductsForSearchValue(String searchDescription, double searchMinPrice,
			double searchMaxPrice) {
		return rt.getForEntity(productUri + "search?searchDescription=" + searchDescription + "&searchMinPrice="
				+ searchMinPrice + "&searchMaxPrice=" + searchMaxPrice, Product[].class);
	}

}
