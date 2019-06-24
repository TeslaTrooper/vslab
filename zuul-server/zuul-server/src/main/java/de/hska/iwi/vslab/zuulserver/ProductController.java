package de.hska.iwi.vslab.zuulserver;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.zuulserver.dto.JSONProduct;

@RestController
@RequestMapping(value = "/products/")
public class ProductController {

	private final ContentClient client;

	@Autowired
	public ProductController(final ContentClient client) {
		this.client = client;
	}

	@PostMapping
	public Product create(@RequestBody JSONProduct product) {
		return client.createProduct(product).getBody();
	}

	@GetMapping
	public Product[] getProducts() {
		return client.getProducts();
	}
	
	@GetMapping("{id}")
	public Product getProduct(@PathVariable final int id) {
		return client.getProductById(id);
	}
	
	@DeleteMapping("{id}")
	public Response deleteProduct(@PathVariable final int id) {
		boolean success = client.deleteProduct(id);
		
		if (success)
			return Response.ok().build();

		return Response.serverError().build();
	}

}
