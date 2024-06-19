package com.mysecurity.dao;

import java.util.List;

import com.mysecurity.model.Customer;

public interface CustomerDAO {

  List<Customer> 	findCustomerByCustomerName(String name);
}
