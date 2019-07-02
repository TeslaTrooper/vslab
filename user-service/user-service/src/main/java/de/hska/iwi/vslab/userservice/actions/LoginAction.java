package de.hska.iwi.vslab.userservice.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hska.iwi.vslab.userservice.controller.json.JSONUser;
import de.hska.iwi.vslab.userservice.db.UserRepo;
import de.hska.iwi.vslab.userservice.db.dataobjects.User;

@Component
public class LoginAction {

	private final UserRepo userRepo;

	@Autowired
	public LoginAction(final UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public void login(final JSONUser jSONUser) throws Exception {
		if (!isValid(jSONUser))
			throw new IllegalArgumentException("Username or password missing!");

		User user = userRepo.findByUsername(jSONUser.getUsername());
		if (user == null)
			throw new Exception("No user found!");

		if (!user.getPassword().equals(jSONUser.getPassword()))
			throw new Exception("Wrong password!");

		// TODO: Save session
	}

	private boolean isValid(final JSONUser jSONUser) {
		String username = jSONUser.getUsername();
		String password = jSONUser.getPassword();
		
		if (username == null || username.isEmpty())
			return false;

		if (password == null || password.isEmpty())
			return false;

		return true;
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void deleteUserById(int userid) {
		userRepo.deleteById((long) userid);
	}
}
