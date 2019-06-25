package de.hska.iwi.vslab.productservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER");
		/* oder
		* auth.userDetailsService(userDetailsService); */

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
		.and()
		.formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
		.and()
		.authorizeRequests().anyRequest().authenticated();
	}
	
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	   @Override
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	   }
	
}
