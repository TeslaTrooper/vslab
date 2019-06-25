package de.hska.iwi.vslab.contentmanagementservice;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.contentmanagementservice.clients.ProductClient;
import de.hska.iwi.vslab.contentmanagementservice.dto.JSONProduct;

@RestController
@RequestMapping(value = "/products/")
public class ProductController {

	private ProductClient productClient = new ProductClient();

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody JSONProduct p) {
		return productClient.createProduct(p);
	}

	@GetMapping
	public ResponseEntity<Product[]> getProducts() {
		return productClient.getProducts();
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		return productClient.getProductById(id);
	}

	@DeleteMapping("{id}")
	public Response deleteProductById(@PathVariable int id) {
		boolean deleted = productClient.deleteProduct(id);
		
		if (deleted)
			return Response.ok().build();

		return Response.serverError().build();
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ResponseEntity<Product[]> getProductsForSearchValue(@RequestParam String searchDescription,
			@RequestParam double searchMinPrice, @RequestParam double searchMaxPrice) {
		return productClient.getProductsForSearchValue(searchDescription, searchMinPrice, searchMaxPrice);
	}

}
