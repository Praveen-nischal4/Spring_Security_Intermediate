package com.mysecurity.service;

import java.util.ArrayList;
import java.util.List;

import com.mysecurity.dao.CustomerDAO;
import com.mysecurity.model.Customer;
import com.mysecurity.model.MySecurityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	List<Customer> customer =	customerDAO.findCustomerByCustomerName(username);
	
	if(customer.isEmpty())
	{
		throw new UsernameNotFoundException(username+"not found");
	}
		
	 
	//ArrayList<GrantedAuthority> authorities = new ArrayList<>();
	
	//SimpleGrantedAuthority roles = new SimpleGrantedAuthority(customer.getRoles());	
	//authorities.add(roles);
	
	
		//return new User(customer.getUsername(), customer.getPassword(), authorities);
	
// UserDetails myCustomer =	User.withUsername(customer.get(0).getUsername())
//	.password(customer.get(0).getPassword())
//	.authorities(customer.get(0).getRoles())
//	.build();
	
	
	
	
	
	
	
	return new MySecurityUser(customer.get(0).getUsername(),customer.get(0).getPassword(),customer.get(0).getRoles());
	}

}
