package com.mysecurity.authprovider;

import java.util.ArrayList;
import java.util.List;

import com.mysecurity.dao.CustomerDAO;
import com.mysecurity.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCustomFormAuthenticatonProvider implements AuthenticationProvider {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private PasswordEncoder passwordEncder; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		String username = authentication.getName();
	 String password =	authentication.getCredentials().toString();
	 
	 //connect with db and get username and password
	 
	List<Customer> customer = customerDAO.findCustomerByCustomerName(username);
	
	if(customer.size() >0)
	{
	 
	 boolean isMatches = passwordEncder.matches(password, customer.get(0).getPassword());
	 
	//grant roles & create authentication and retunr it
	    if(isMatches)
	    {
	    	
	    	String roles = customer.get(0).getRoles();
	    	
	    	ArrayList<GrantedAuthority> authorities = new ArrayList<>();	    	
	    	SimpleGrantedAuthority role1 = new SimpleGrantedAuthority(roles);
	    	authorities.add(role1);
	    	
	    	return new UsernamePasswordAuthenticationToken(username,password, authorities);
	    	
	    }else
	    {
	    	throw new BadCredentialsException("Your username or password is incorrect");
	    }	
	  
	}else
	{
		throw new BadCredentialsException("User does'nt exist");
	}
	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
