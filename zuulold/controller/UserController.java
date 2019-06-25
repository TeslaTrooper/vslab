package de.hska.iwi.vslab.zuulserver.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.zuulserver.ContentClient;
import de.hska.iwi.vslab.zuulserver.dto.Registration;
import de.hska.iwi.vslab.zuulserver.dto.User;

@RestController
@RequestMapping(value = "/users/")
public class UserController {

	private final ContentClient client;

	@Autowired
	public UserController(final ContentClient client) {
		this.client = client;
	}

	@PostMapping
	public Response create(@RequestBody final Registration jSONRegistration) {
		User user = client.createUser(jSONRegistration);

		return Response.ok(user).build();
	}

	@PostMapping("login")
	public Response login(@RequestBody final User jSONUser) {
		User user = client.loginUser(jSONUser);

		return Response.ok(user).build();
	}

	@PostMapping("logout")
	public Response logout(@RequestBody final User jSONUser) {
		User user = client.logoutUser(jSONUser);

		return Response.ok(user).build();
	}

}
