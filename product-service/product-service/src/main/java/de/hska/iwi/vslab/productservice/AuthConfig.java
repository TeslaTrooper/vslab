package de.hska.iwi.vslab.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(authenticationManager)
				.tokenStore(tokenStore()); // TODO user repo
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("clientId").secret("clientsecret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid");
	

	// @formatter:off
	/*
	 * clients.inMemory() .withClient("my-trusted-client")
	 * .authorizedGrantTypes("password", "authorization_code", "refresh_token",
	 * "implicit") .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
	 * .scopes("read", "write", "trust") .resourceIds("oauth2-resource")
	 * .accessTokenValiditySeconds(600) .and()
	 * .withClient("my-client-with-registered-redirect")
	 * .authorizedGrantTypes("authorization_code") .authorities("ROLE_CLIENT")
	 * .scopes("read", "trust") .resourceIds("oauth2-resource")
	 * .redirectUris("http://anywhere?key=value") .and()
	 * .withClient("my-client-with-secret")
	 * .authorizedGrantTypes("client_credentials", "password")
	 * .authorities("ROLE_CLIENT") .scopes("read") .resourceIds("oauth2-resource")
	 * .secret("secret");
	 */
	// @formatter:on
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		// creates authorization codes, stores the codes in memory.
		return new InMemoryAuthorizationCodeServices();
	}
}
