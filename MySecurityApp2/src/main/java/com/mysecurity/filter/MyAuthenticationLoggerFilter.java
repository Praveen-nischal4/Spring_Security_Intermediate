package com.mysecurity.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MyAuthenticationLoggerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	//	String username = request.getParameter("username");
		//System.out.println("Your username is :"+username);
            //chain.doFilter(request, response);
		
	 Authentication userAuthentication	= SecurityContextHolder.getContext().getAuthentication();
	 
	 if(userAuthentication !=null)
	 {
	 System.out.println("Your username is :"+userAuthentication.getName()+" And your Authorities :"+userAuthentication.getAuthorities());
	}
	 
	 chain.doFilter(request, response);
	}

}
