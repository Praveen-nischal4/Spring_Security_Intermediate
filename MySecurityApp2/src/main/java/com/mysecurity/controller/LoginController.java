package com.mysecurity.controller;

import javax.sql.DataSource;

import com.mysecurity.dao.SingupDAO;
import com.mysecurity.model.SignupDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private SingupDAO signupDAO;
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
	@GetMapping("/myLogin")
	public String getLogin()
	{
		return "Login";
		
	}
	
	@GetMapping("/signup")
	public String signUp(@ModelAttribute("signupdto") SignupDTO signupDTO)
	{
		return "sign_up";
		
	}
	
	@PostMapping("/process-signup")
	public String getSignup(SignupDTO signupDTO)
	{
		//before encoding 
		System.out.println(signupDTO);
		
	String encodedPassword = bcryptPasswordEncoder.encode(signupDTO.getPassword());         //encode password
	
	signupDTO.setPassword(encodedPassword);
	
	//After encoding 
			System.out.println(signupDTO);
			
			
	//finally save in database
			
		//	signupDAO.saveUser(signupDTO);
		
			// now we use new way to save in database using default coding done by spring developers
			
		//JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		//jdbcUserDetailsManager.setDataSource(datasource);
		
		//note this line of code for accessing user from signup form
		
	UserDetails myUser = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword()).authorities("Coder").build();
		
		userDetailsManager.createUser(myUser);
			
			
		return "redirect:/myLogin";
		
	}
	
	
}
