package hska.iwi.eShopMaster.controller.manager;


import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.List;

public class CategoryManagerImpl {

	
	public CategoryManagerImpl() {
//		helper = new CategoryDAO();
	}

	public List<Category> getCategories() {
		return null;
//		return helper.getObjectList();
	}

	public Category getCategory(int id) {
		return null;
//		return helper.getObjectById(id);
	}

	public Category getCategoryByName(String name) {
		return null;
//		return helper.getObjectByName(name);
	}

	public void addCategory(String name) {
		Category cat = new Category(name);
//		helper.saveObject(cat);

	}

	public void delCategory(Category cat) {
	
// 		Products are also deleted because of relation in Category.java 
//		helper.deleteById(cat.getCategoryId());
	}

	public void delCategoryById(int id) {
		
//		helper.deleteById(id);
	}
}
