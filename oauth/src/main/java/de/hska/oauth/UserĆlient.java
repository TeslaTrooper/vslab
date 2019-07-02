package de.hska.oauth;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserĆlient {
	
	public User getUserByUsername(String username) {
		return new RestTemplate().getForObject("http://user-service:8762/users/" + username, User.class);
	}

}
