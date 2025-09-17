package com.globant.poc.service;

import java.util.List;

import com.globant.poc.dto.CustomerDTO;
import com.globant.poc.model.Customer;
import com.globant.poc.model.embeddedIds.CustomerId;

public interface CustomerServiceStrategy {
    List<CustomerDTO> getCustomerByLastName(String lastName);

    Customer saveCustomer(Customer customer);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) throws IllegalAccessException;

    Customer getCustomer (CustomerId custermerId);
}
