package de.hska.iwi.vslab.categoryservice.actions;

public class InitCategorySiteAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1108136421569378914L;

	private String pageToGoTo;
//	private User user;

//	private List<Category> categories;

	public String execute() throws Exception {
		
		String res = "input";

//		Map<String, Object> session = ActionContext.getContext().getSession();
//		user = (User) session.get("webshop_user");
//		boolean isAdmin = true;
//		if(user != null && isAdmin) {
//
//			CategoryManager categoryManager = new CategoryManagerImpl();
//			this.setCategories(categoryManager.getCategories());
//			
//			if(pageToGoTo != null){
//				if(pageToGoTo.equals("c")){
//					res = "successC";	
//				}
//				else if(pageToGoTo.equals("p")){
//					res = "successP";
//				}				
//			}
//		}
		
		return res;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

	public String getPageToGoTo() {
		return pageToGoTo;
	}

	public void setPageToGoTo(String pageToGoTo) {
		this.pageToGoTo = pageToGoTo;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}
