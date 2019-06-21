package de.hska.iwi.vslab.zuulserver;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories/")
public class CategoryContoller {

	private final ContentClient client;

	@Autowired
	public CategoryContoller(final ContentClient client) {
		this.client = client;
	}

	@PostMapping("{name}")
	public Category create(@PathVariable("name") String name) {
		return client.createCategory(name).getBody();
	}

	@GetMapping
	public Category[] getCategories() {
		return client.getCategories();
	}

	@GetMapping("{id}")
	public Category getCategory(@PathVariable final int id) {
		return client.getCategoryById(id);
	}

	@DeleteMapping("{id}")
	public Response deleteCategory(@PathVariable final int id) {
		boolean success = client.deleteCategory(id);

		if (success)
			return Response.ok().build();

		return Response.serverError().build();
	}

}
