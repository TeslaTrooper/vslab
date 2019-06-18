package de.hska.iwi.vslab.contentmanagementservice.clients;

import de.hska.iwi.vslab.contentmanagementservice.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CategoryClient {

	private String categoryUri = "http://categories/categories/";




	public boolean createCategory(Category c) {

		RestTemplate rt = new RestTemplate();
		rt.postForEntity(categoryUri, c, Category.class );

		return true;
	}
	
	public Category[] getCategories() {

		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category[]> entity = rt.getForEntity(categoryUri, Category[].class);

		return entity.getBody();
	}
	
	
	public Category getCategoryById(int id) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Category> entity = rt.getForEntity(categoryUri+"/"+id, Category.class);

		return entity.getBody();
	}
	
	
	public boolean deleteCategory(int id) {

		RestTemplate rt = new RestTemplate();
		rt.delete(categoryUri+"/"+id);



		return true;
	}
	

	
	
}
