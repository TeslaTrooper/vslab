package de.hska.iwi.vslab.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.userservice.actions.LoginAction;
import de.hska.iwi.vslab.userservice.actions.LogoutAction;
import de.hska.iwi.vslab.userservice.actions.RegisterAction;
import de.hska.iwi.vslab.userservice.controller.json.JSONRegistration;
import de.hska.iwi.vslab.userservice.controller.json.JSONUser;
import de.hska.iwi.vslab.userservice.db.UserRepo;
import de.hska.iwi.vslab.userservice.db.dataobjects.User;

@RestController
@RequestMapping(value = "/users/")
public class UserController {

	private final RegisterAction registerAction;
	private final LoginAction loginAction;
	private final LogoutAction logoutAction;


	@Autowired
	public UserController(final RegisterAction registerAction, final LoginAction loginAction,
			final LogoutAction logoutAction) {
		this.registerAction = registerAction;
		this.loginAction = loginAction;
		this.logoutAction = logoutAction;
		
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody final JSONRegistration jSONRegistration) {
		try {
			System.out.println(String.format("register: %s, %s, %s, %s, %s", jSONRegistration.getUsername(), jSONRegistration.getFirstname(), jSONRegistration.getLastname(),jSONRegistration.getPassword1(),jSONRegistration.getPassword2()));
			return new ResponseEntity<>(registerAction.register(jSONRegistration), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody final JSONUser jSONUser) {
		try {
			System.out.println("login: " + jSONUser.getUsername() + ", " + jSONUser.getPassword());
			loginAction.login(jSONUser);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("logout")
	public ResponseEntity<User> logout(@RequestBody final JSONUser jSONUser) {
		try {
			logoutAction.logout();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		try {
			loginAction.getUserByUsername(username);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@DeleteMapping("{userid}")
	public ResponseEntity<User> deleteUser(@PathVariable int userid) {
		try {
			loginAction.deleteUserById(userid);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
