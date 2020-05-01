package com.ds.service;

import java.util.List;

import com.ds.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomers(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);
}
