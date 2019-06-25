package hska.iwi.eShopMaster.controller;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class AddCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6704600867133294378L;
	
	private String newCatName = null;
	
	private List<Category> categories;
	
	User user;
	
	private String categoryUrl = "http://localhost:8770/categories/";

	public String execute() throws Exception {
		String res = "input";
		this.validate();
		HttpResponse<JsonNode> response = Unirest.post(categoryUrl + "/" + newCatName)
			      .header("accept", "application/json").asJson();
		
		
		getCategories().add(new Gson().fromJson(response.getBody().toString(), Category.class));
		

//		Map<String, Object> session = ActionContext.getContext().getSession();
//		user = (User) session.get("webshop_user");
//		if(user != null && (user.getRole().getTyp().equals("admin"))) {
//			// Add category
//			RequestHelper<String, Void> helper = new RequestHelper<String, Void>();
//			helper.fetchContent(categoryUrl, "POST", newCatName);
//			
//			// Go and get new Category list
//			RequestHelper<Void, List<Category>> helper2 = new RequestHelper<Void, List<Category>>();
//			this.setCategories(helper2.fetchContent(categoryUrl, "GET", null));
			
			res = "success";
//		}
		
		return res;
	
	}
	
	@Override
	public void validate(){
		if (getNewCatName().length() == 0) {
			addActionError(getText("error.catname.required"));
		}
		// Go and get new Category list
		HttpResponse<JsonNode> response = Unirest.post(categoryUrl)
			      .header("accept", "application/json").asJson();
		
		
		setCategories(Arrays.asList(new Gson().fromJson(response.getBody().toString(), Category[].class)));
//		CategoryManager categoryManager = new CategoryManagerImpl();
//		this.setCategories(categoryManager.getCategories());
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String getNewCatName() {
		return newCatName;
	}

	public void setNewCatName(String newCatName) {
		this.newCatName = newCatName;
	}
}
