package hska.iwi.eShopMaster.controller.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import hska.iwi.eShopMaster.controller.oauth.Oauth;
import hska.iwi.eShopMaster.model.database.dataobjects.ApiProduct;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public class ProductManagerImpl {
	
	private static final String PRODUCT_URL = "http://localhost:8770/products";
	private final OAuth2RestTemplate oAuth2RestTemplate;
	
	public ProductManagerImpl() {
		oAuth2RestTemplate = Oauth.getOAuth2RestTemplate();
	}

	public List<Product> getProducts() {
		return null;
//		return helper.getObjectList();
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {
				return null;	
//		return new ProductDAO().getProductListByCriteria(searchDescription, searchMinPrice, searchMaxPrice);
	}

	public Product getProductById(int id) {
		return oAuth2RestTemplate.getForEntity(PRODUCT_URL + "/" + id, Product.class).getBody();
	}

	public Product getProductByName(String name) {
		
		List<Product> ps =
				new ArrayList<Product>(Arrays.asList(
						oAuth2RestTemplate.getForEntity(PRODUCT_URL, Product[].class).getBody()));
		for(Product p: ps) {
			if(p.getName() == name) {
				return p;
			}
		}
		return null;
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		int productId = -1;
		
		CategoryManagerImpl categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);
		
		if(category != null){
			ApiProduct product= new ApiProduct(name, category.getCategoryId(), price);
			if(details != null){
				product.setDetails(details);
			}
			
			try {
				Product p = oAuth2RestTemplate.postForEntity(PRODUCT_URL, name, Product.class).getBody();
				productId = p.getProductId();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			 
		return productId;
	}
	

	public void deleteProductById(int id) {
		try {	
			oAuth2RestTemplate.delete(PRODUCT_URL + "/" + id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		List<Product> ps =
				new ArrayList<Product>(Arrays.asList(
						oAuth2RestTemplate.getForEntity(PRODUCT_URL + "/" + categoryId, Product[].class).getBody()));
		for(Product p: ps) {
			this.deleteProductById(p.getProductId());
		}
		return false;
	}

}
