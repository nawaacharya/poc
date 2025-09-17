package com.globant.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globant.poc.model.Customer;
import com.globant.poc.model.embeddedIds.CustomerId;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {

    @Query(name = "Customer.findByLastName")
    List<Customer> findCustomersByLastName(@Param("lastName") String lastName);

    @Query("SELECT c FROM Customer c")
    List<Customer> getAllCustomers();

    @Query("SELECT c FROM Customer c WHERE c.customerId.lastName = :lastName AND c.customerId.dateOfBirth = :dob")
    Customer getCustomer(@Param("lastName") String lastName, @Param("dob") String dob);
}
