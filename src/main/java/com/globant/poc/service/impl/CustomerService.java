package com.globant.poc.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.globant.poc.converter.CustomersConverter;
import com.globant.poc.dto.CustomerDTO;
import com.globant.poc.exception.CustomerNotFoundException;
import com.globant.poc.model.Customer;
import com.globant.poc.model.embeddedIds.CustomerId;
import com.globant.poc.repository.CustomerRepository;
import com.globant.poc.service.CustomerServiceStrategy;
import com.globant.poc.util.PatchUtils;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CustomerService implements CustomerServiceStrategy {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
   // @Cacheable(value = "customers", key = "#lastName")
    public List<CustomerDTO> getCustomerByLastName(String lastName) {
        List<Customer> customers = customerRepository.findCustomersByLastName(lastName);
        if (customers != null) {
            return customers.stream().map(CustomersConverter::convertCustomer).toList();
        }
        return null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return createCustomer(UUID.randomUUID().toString(), customerDTO);
    }

    @CachePut(value = "customers", key = "#result.id")
    public CustomerDTO createCustomer(String id, CustomerDTO customerDTO) {
        Customer customer = CustomersConverter.convertCustomerDTO(customerDTO);
        customer = customerRepository.save(customer);
        customer.setId(id);
        return CustomersConverter.convertCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) throws IllegalAccessException {
        Customer previousCustomer = getCustomerById(id);
        Customer currentCustomer = CustomersConverter.convertCustomerDTO(customerDTO);
        if (previousCustomer != null && currentCustomer != null) {
            Customer updatedCustomer = PatchUtils.patchUpdatedFields(previousCustomer, currentCustomer);
            customerRepository.save(updatedCustomer);
            return CustomersConverter.convertCustomer(id, updatedCustomer);
        }
        throw new CustomerNotFoundException();
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomerById(String id) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cache not found");
        List<Customer> customers = customerRepository.getAllCustomers();
        return customers != null ? customers.get(0) : null;
    }

    public CustomerDTO getCustomerDTOById(String id) {
        return CustomersConverter.convertCustomer(id, getCustomerById(id));
    }

    @Override
    public Customer getCustomer(CustomerId customerId) {
        return customerRepository.getCustomer(customerId.getLastName(), customerId.getDateOfBirth());
    }

}
