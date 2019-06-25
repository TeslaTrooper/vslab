package hska.iwi.eShopMaster.controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import hska.iwi.eShopMaster.model.database.dataobjects.UserLogin;
import kong.unirest.Unirest;

public class LogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530488065543708898L;
// TODO get user from somewhere
	
	private String username = null;
	private String password = null;	
	private static final String USER_URL = "http://localhost:8770/users/logout";
	

	@Override
	public String execute() throws Exception {

		// Return string:
		String result = "input";
		
		this.validate();
		
		Gson gson = new Gson();
		UserLogin u = new UserLogin(username, password);
		
		Unirest.post(USER_URL)
	      .header("accept", "application/json")
	      .body(gson.toJson(u))
	      .asJson();

		result = "success";

		return result;
		
	}
}
