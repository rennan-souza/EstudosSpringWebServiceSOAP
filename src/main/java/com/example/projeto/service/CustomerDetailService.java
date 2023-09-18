package com.example.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.projeto.bean.Customer;
import com.example.projeto.helper.Status;

@Component
public class CustomerDetailService {

	private static List<Customer> customers = new ArrayList<>();
	
	static {
		Customer c1 = new Customer(1, "Frajola", "(11)91234-1234", "email@email.com.br");
		customers.add(c1);
		
		Customer c2 = new Customer(2, "Papaleguas", "(11)91234-1234", "email@email.com.br");
		customers.add(c2);
		
		Customer c3 = new Customer(3, "Mickey", "(11)91234-1234", "email@email.com.br");
		customers.add(c3);
		
		Customer c4 = new Customer(4, "Jerry", "(11)91234-1234", "email@email.com.br");
		customers.add(c4);
		
		Customer c5 = new Customer(5, "Tom", "(11)91234-1234", "email@email.com.br");
		customers.add(c5);
	}
	
	public Customer findById(int id) {
		Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findAny();
		if (customer.isPresent()) {
			return customer.get();
		}
		return null;
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Status deleteById(int id) {
		Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findAny();
		if (customer.isPresent()) {
			customers.remove(customer.get());
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}
}
