package de.hska.iwi.vslab.categoryservice.actions;


public class DeleteCategoryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254575994729199914L;
	
	private int catId;
//	private List<Category> categories;

	public String execute() throws Exception {
		
		String res = "input";
		
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		User user = (User) session.get("webshop_user");
//		
//		if(user != null && (user.getRole().getTyp().equals("admin"))) {
//
//			// Helper inserts new Category in DB:
//			CategoryManager categoryManager = new CategoryManagerImpl();
//		
//			categoryManager.delCategoryById(catId);
//
//			categories = categoryManager.getCategories();
//				
//			res = "success";
//
//		}
		
		return res;
		
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}
}
