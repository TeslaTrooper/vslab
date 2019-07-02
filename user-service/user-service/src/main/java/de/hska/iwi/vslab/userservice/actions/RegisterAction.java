package de.hska.iwi.vslab.userservice.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hska.iwi.vslab.userservice.controller.json.JSONRegistration;
import de.hska.iwi.vslab.userservice.db.RoleRepo;
import de.hska.iwi.vslab.userservice.db.UserRepo;
import de.hska.iwi.vslab.userservice.db.dataobjects.Role;
import de.hska.iwi.vslab.userservice.db.dataobjects.User;

@Component
public class RegisterAction {

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	private static final Role ADMIN = new Role("ADMIN", 0);
	private static final Role USER = new Role("USER", 1);

	@Autowired
	public RegisterAction(final UserRepo userRepo, final RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		
		roleRepo.deleteAll();
		roleRepo.save(ADMIN);
		roleRepo.save(USER);
		
//		userRepo.deleteAll();
//		userRepo.save(new User("admin", "admin", "admin", "admin", ADMIN));
	}

	public User register(final JSONRegistration jSONRegistration) throws Exception {
		Role role = roleRepo.getRoleByLevel(1); // 1 -> regular User, 2 -> Admin

		if (userRepo.existsByUsername(jSONRegistration.getUsername()))
			throw new IllegalStateException("User already exists!");

		if (!isValid(jSONRegistration))
			throw new IllegalArgumentException("Invalid user!");

		User user = new User(jSONRegistration.getUsername(), jSONRegistration.getFirstname(),
				jSONRegistration.getLastname(), jSONRegistration.getPassword1(), role);
		userRepo.save(user);
		
		return user;
	}

	private boolean isValid(final JSONRegistration jSONRegistration) {
		if (jSONRegistration.getFirstname().isEmpty())
			return false;

		if (jSONRegistration.getLastname().isEmpty())
			return false;

		if (jSONRegistration.getUsername().isEmpty())
			return false;

		if (jSONRegistration.getPassword1().isEmpty())
			return false;

		if (jSONRegistration.getPassword2().isEmpty())
			return false;

		if (!jSONRegistration.getPassword1().equals(jSONRegistration.getPassword2()))
			return false;

		return true;
	}

}
