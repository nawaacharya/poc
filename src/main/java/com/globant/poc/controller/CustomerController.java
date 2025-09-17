package com.globant.poc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.poc.dto.CustomerDTO;
import com.globant.poc.service.impl.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CustomerController", description = "CRUD operations on customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find/{lastName}")
    @Operation(description = "Get list of customers by last name")
    public List<CustomerDTO> getAllCustomers(@PathVariable String lastName) {
       return customerService.getCustomerByLastName(lastName);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get list of customer by cache id")
    public CustomerDTO getCustomerById(@PathVariable String id) {
       return customerService.getCustomerDTOById(id);
    }

    @PostMapping("/add")
    @Operation(description = "Create a new customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PatchMapping("/update/{id}")
    public CustomerDTO updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) throws IllegalAccessException {
        return customerService.updateCustomer(id, customerDTO);
    }

}
