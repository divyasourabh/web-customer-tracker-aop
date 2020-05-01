package com.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds.dao.CustomerDAO;
import com.ds.entity.Customer;
import com.ds.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

//	Logic move to service layer
//	@Autowired
//	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerService customerService;
	
//	@RequestMapping("/list")
	@GetMapping("/list") //added in Spring 4.3
	public String listCustomersString(Model model) {
		
//		Logic move to service layer
//		List<Customer> customers = customerDAO.getCustomers();
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd (Model model) {
		
		Customer customer = new Customer();
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String saveCustomer(@RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = customerService.getCustomers(customerId);
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId, Model model) {
		
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {
		
		// search customers from the service
        List<Customer> customers = customerService.searchCustomers(searchName);
                
        // add the customers to the model
        model.addAttribute("customers", customers);
		
		return "list-customers";
	}	
	
}
