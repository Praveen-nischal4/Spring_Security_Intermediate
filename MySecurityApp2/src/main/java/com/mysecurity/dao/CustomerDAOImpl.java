package com.mysecurity.dao;

import java.util.List;

import com.mysecurity.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Customer> findCustomerByCustomerName(String name) {
		
		String sql ="select * from customer where username=?";
		
		Object[] args = {name};
	//Customer customer =	jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Customer>(Customer.class));
	
	List<Customer> customer =	jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Customer>(Customer.class));
		return customer;
	}

}
