package hska.iwi.eShopMaster.controller.manager;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import hska.iwi.eShopMaster.controller.oauth.Oauth;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import hska.iwi.eShopMaster.model.database.dataobjects.UserLogin;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl {

	
	private static final String USER_URL = "http://localhost:8770/users";
	
	public void registerUser(String username, String name, String lastname, String password, Role role) {

		User user = new User(username, name, lastname, password, role);

		// helper.saveObject(user);
	}
	

	public User login(String username, String password) {
		UserLogin u = new UserLogin(username, password);
		if (username == null || username.equals("")) {
			return null;
		}
		try {
			OAuth2RestTemplate oAuth2RestTemplate = Oauth.createOAuth2RestTemplate(username, password);
			User user = oAuth2RestTemplate.getForEntity(USER_URL, User.class, u).getBody();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
}

	
	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}

		return Oauth.getDefaultRestTemplate().getForEntity(USER_URL, User.class, username).getBody();
	}

	public boolean deleteUserById(int id) {
//		User user = new User();
//		user.setUserId(id);
//		helper.deleteObject(user);
		return true;
	}

	public Role getRoleByLevel(int level) {
//		RoleDAO roleHelper = new RoleDAO();
//		return roleHelper.getRoleByLevel(level);
		return null;
	}

	public boolean doesUserAlreadyExist(String username) {
		
    	User dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}

}
