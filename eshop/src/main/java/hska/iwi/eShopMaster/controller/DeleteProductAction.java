package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.controller.manager.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666796923937616729L;

	private int id;

	public String execute() throws Exception {
		
		String res = "input";
		
		ProductManagerImpl productManager = new ProductManagerImpl();
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		
		if(user != null && (user.getRole().getTyp().equals("admin"))) {

			productManager.deleteProductById(id);
			res = "success";
			
		}
		
		return res;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
