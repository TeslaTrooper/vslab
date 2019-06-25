package hska.iwi.eShopMaster.controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import hska.iwi.eShopMaster.model.database.dataobjects.UserLogin;
import kong.unirest.Unirest;

public class LoginAction extends ActionSupport {

	/**
     *
     */
	private static final long serialVersionUID = -983183915002226000L;
	private String username = null;
	private String password = null;
	private String firstname;
	private String lastname;
	private String role;
	
	private static final String USER_URL = "http://localhost:8770/users/login";
	

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


//		UserManager myCManager = new UserManagerImpl();
//		
//		// Get user from DB:
//		RequestHelper<User, User> helper = new RequestHelper<User, User>();
//		// TODO: role
//		User user = new User(username, firstname, lastname, password, new Role(role, 1));
//		User u = helper.fetchContent(USER, "POST", user);
//		if(u != null) {
//			result = "success";
//		}
////		User user = myCManager.getUserByUsername(getUsername());
//
////		// Does user exist?
////		if (u != null) {
////			// Is the password correct?
////			if (user.getPassword().equals(getPassword())) {
////				// Get session to save user role and login:
////				Map<String, Object> session = ActionContext.getContext().getSession();
////				
////				// Save user object in session:
////				session.put("webshop_user", user);
////				session.put("message", "");
////				firstname= user.getFirstname();
////				lastname = user.getLastname();
////				role = user.getRole().getTyp();
//				
////			}
////			else {
////				addActionError(getText("error.password.wrong"));
////			}
////		}
////		else {
////			addActionError(getText("error.username.wrong"));
////		}

//		return result;
	}
	
	@Override
	public void validate() {
		if (getUsername().length() == 0) {
			addActionError(getText("error.username.required"));
		}
		if (getPassword().length() == 0) {
			addActionError(getText("error.password.required"));
		}
	}

	public String getUsername() {
		return (this.username);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return (this.password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
