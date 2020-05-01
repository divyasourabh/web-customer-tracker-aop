package com.ds.dao;

import java.util.List;

import com.ds.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);
}
