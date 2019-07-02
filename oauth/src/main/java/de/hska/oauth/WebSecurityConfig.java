package de.hska.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("pw")).roles("USER").and().withUser("admin")
				.password(passwordEncoder().encode("pw")).roles("USER", "ADMIN");
		/*
		 * oder auth.userDetailsService(userDetailsService);
		 */

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access").and().formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error").and().authorizeRequests().anyRequest()
				.authenticated();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
