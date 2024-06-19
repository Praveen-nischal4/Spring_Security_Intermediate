package com.mysecurity.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import com.mysecurity.authprovider.MyCustomFormAuthenticatonProvider;
import com.mysecurity.filter.MyAuthenticationLoggerFilter;
import com.mysecurity.service.CustomerUserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@EnableWebSecurity(debug = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	//i want to create some details for user
	// username,password,roles
	// Default password is 955455Pra
	
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder; 

	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	private CustomerUserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private MyCustomFormAuthenticatonProvider custAuthProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	 /*	auth.inMemoryAuthentication()
		.withUser("Praveen").password("$2a$10$6unANv6C2jmVXUnJvL.VSuxGwAQpA6Kj.13uKKZwubUfxEcUVglWK").roles("admin")
		.and()
		.withUser("Raju").password("$2a$10$.EACjK492stlEEBRyObCPOPscYloeWyRIQNtr4h/AiSxKEC3dePSq").roles("user"); 
		
		System.out.println("Encoded password ="+bcryptPasswordEncoder.encode("Raju"));
		
		*/
		
		// code to connect user from DB i.e Database
		
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select username,password,enabled from customer where username=?")
//		.authoritiesByUsernameQuery("select username,roles from customer where username=?")
//		.passwordEncoder(bcryptPasswordEncoder);	
//		
//		
                 //custom userdetails service  	 ie default authentication provider
		
		//auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bcryptPasswordEncoder);
		
		//but when using customer authentication provider
		
		auth.authenticationProvider(custAuthProvider);
		
	}   
	
	
	 /*
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//let's do some in-memory authentication
		
//		ArrayList<GrantedAuthority> roles =new ArrayList<GrantedAuthority>();
//		
//		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("Trainer");
//		SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("Coder");
//		
//		roles.add(role1);
//		roles.add(role2);
//		
//		User myuser = new User("myuser", "pingpong", roles);
//		
//		User.withUsername("Amul").password("amul123").roles("Admin","User").build();
//		
		
		
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		
		
		//3rd way to create user in spring security
	  UserDetails myUser = User.withUsername("Rashmi").password("$2a$10$JArkNsmOFG6bQzipv.zjqeOIeigwbw4.7ht3OM75mQ4W3ZzsCQHLS").roles("Trainer","Coder").build();
	  UserDetails myUsr = User.withUsername("Shivani").password("$2a$10$JArkNsmOFG6bQzipv.zjqeOIeigwbw4.7ht3OM75mQ4W3ZzsCQHLS").roles("Coder").build();	
	
	  userDetailsManager.createUser(myUser);
	  userDetailsManager.createUser(myUsr);
	  auth.userDetailsService(userDetailsManager);
		
	}
	
	*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

     http
     .addFilterAfter(new MyAuthenticationLoggerFilter(), BasicAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers("/coder").hasAuthority("Coder")    
      .antMatchers("/trainer").hasAuthority("Trainer")
      .antMatchers("/").authenticated()
      .and()
      .formLogin().loginPage("/myLogin").loginProcessingUrl("/process-login").permitAll()
      .and()
      .httpBasic()
      .and()
      .logout()
      .and()
      .exceptionHandling().accessDeniedPage("/accessDenied");
	}
}
