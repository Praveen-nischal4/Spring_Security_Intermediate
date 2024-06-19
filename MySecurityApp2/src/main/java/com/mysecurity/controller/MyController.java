package com.mysecurity.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import javax.sql.DataSource;

import com.mysecurity.model.ChangePasswordDTO;
import com.mysecurity.model.SignupDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	/*
	@Autowired
	private DataSource datasource;
	*/
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
	/*
	//Principle means user name
	
	@RequestMapping("/")
	public String myHome(Principal principle)
	{
		
		
		String username = principle.getName();
		System.out.println(" Hello "+username);
		
		return "myhome";
	}
	*/
	
	@RequestMapping("/")
	public String myHome(Principal principle,Authentication auth,Model model)
	{
		 
	    String username = "";                     // Initialize user name as empty string
	    // Check if principal is not null
	    if (principle != null) {
	       
	        username = principle.getName();                // Fetch user name
	        System.out.println(username);
	    }

	    // Fetch authorities
	    Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
	    
	    if(auth!=null)
	    {
	    authorities = auth.getAuthorities();
	    System.out.println(authorities);
	    
	    }
	    model.addAttribute("username", username);
	    model.addAttribute("roles", authorities);

	    return "myhome";
		
		
		
		
		/*
		
		//fetching the username
		 String username = "";
		
		 username =principle.getName();
		System.out.println(username);
				
		//fetching authorities
		
	 Collection<? extends GrantedAuthority>  authorities	= auth.getAuthorities();
	 System.out.println(authorities);
	 
	 model.addAttribute("username",username);
	 model.addAttribute("roles", authorities);
	 
		return "myhome";
		*/
	}
	
	
	
	@ResponseBody
	@GetMapping("/bye")
	public String sayBye()
	{
		return "Bye bye its Over";
	}
	
	
	@GetMapping("/trainer")
	public String showTrainerDashboard()
	{
		return "trainer-dashboard";
	}
	
	
	@GetMapping("/coder")
	public String showCoderDashboard()
	{
		return "coder-dashboard";
	}
	
	@GetMapping("/accessDenied")
	public String errorPage()
	{
		return "access-denied";
	}
	
	
	@GetMapping("/deleteUser")
	public String deleteAccount(@RequestParam("username") String username)
	{
		//JdbcUserDetailsManager  userDetailsManager = new JdbcUserDetailsManager();
	//	userDetailsManager.setDataSource(datasource);
		
		userDetailsManager.deleteUser(username);
		
		System.out.println("Account deleted "+username);
		
		return "redirect:/myLogin";
	}
	
	@GetMapping("/editUser")
	public ModelAndView editUser(@RequestParam("username") String username,SignupDTO signupDTO)
	{
		UserDetails myUser =userDetailsManager.loadUserByUsername(username);
		System.out.println(myUser);
		
		SignupDTO mysign = new SignupDTO();
		mysign.setUsername(myUser.getUsername());
		mysign.setPassword(myUser.getPassword());
		mysign.setEnabled(1);
		
		return new ModelAndView("edit-details","command",mysign);
	}
	
	@PostMapping("/updateDetails")
	public String updateDetails(SignupDTO signupDTO)
	{
		UserDetails editedDetails = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword()).authorities("Coder").build();
		
		userDetailsManager.updateUser(editedDetails);
		return "redirect:/myLogin";
	}	
	
	
	@GetMapping("/changePassword")
	public String changePassword(Model model)
	{
		model.addAttribute("password-chng", new ChangePasswordDTO());
		return "change-password";
	}
	
	
	@PostMapping("/save-password")
	public String savePassword(Principal principle ,ChangePasswordDTO changePasswordDTO)
	{
		//System.out.println(changePasswordDTO);
		//write some logic to save 
		UserDetails user = userDetailsManager.loadUserByUsername(principle.getName());
		
		//check password matches or not
		
	boolean matches =	passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
		
	if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPasword()))
	{
		return "redirect:/changePassword?notMatched";
	}
		
		if(matches)
		{
		
		String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
		
		userDetailsManager.changePassword(changePasswordDTO.getOldPassword() ,encodedPassword);
		
		return "redirect:/";
		}
		
		return "redirect:/changePassword?invalidPassword";
	}
}
