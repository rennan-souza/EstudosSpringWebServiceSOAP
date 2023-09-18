package com.example.projeto.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.projeto.bean.Customer;
import com.example.projeto.service.CustomerDetailService;

import br.com.myservicesoap.CustomerDetail;
import br.com.myservicesoap.GetAllCustomerDetailRequest;
import br.com.myservicesoap.GetAllCustomerDetailResponse;
import br.com.myservicesoap.GetCustomerDetailRequest;
import br.com.myservicesoap.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	private CustomerDetailService service;
	
	@PayloadRoot(namespace = "http://myservicesoap.com.br", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest
		(@RequestPayload GetCustomerDetailRequest req) throws Exception {
		Customer customer = service.findById(req.getId());
		if (customer == null){
			throw new Exception("Invalid customer id: " + req.getId());
		}
		return convertToGetCustomerDetailResponse(customer);
	}
	
	@PayloadRoot(namespace = "http://myservicesoap.com.br", localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processGetAllCustomerDetailResponse
		(@RequestPayload GetAllCustomerDetailRequest req) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResponse(customers);
	}
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		return customerDetail;
	}
	
	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetail(c)));
		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
