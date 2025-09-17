package com.globant.poc.converter;

import java.util.ArrayList;
import com.globant.poc.dto.CreditReportDTO;
import com.globant.poc.dto.CustomerDTO;
import com.globant.poc.enums.PhoneType;
import com.globant.poc.model.CreditReport;
import com.globant.poc.model.Customer;
import com.globant.poc.model.embeddedIds.CustomerId;

public class CustomersConverter {

    public static Customer convertCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(new CustomerId(customerDTO.getLastName(), customerDTO.getDob()));
        customer.setEmail(customerDTO.getEmail());
        customer.setFullName(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        customer.setPhoneType(PhoneType.valueOf(customerDTO.getPhoneType().toUpperCase()));
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        if (customerDTO.getCreditReport() != null) {
            CreditReport creditReport = new CreditReport();
            creditReport.setBureu(customerDTO.getCreditReport().getBureu());
            creditReport.setScrore(customerDTO.getCreditReport().getCreditScrore());
            creditReport.setReportDate(customerDTO.getCreditReport().getReportDate());
            creditReport.setCustomer(customer);
            customer.setCreditReport(creditReport);
        }
        if (customerDTO.getLoanApplications() != null) {
            customer.setLoanApplications(new ArrayList<>());
            customerDTO.getLoanApplications().forEach(application -> {
                application.setCustomer(customer);
                customer.getLoanApplications().add(application);
            });
        }
        return customer;
    }

    public static CustomerDTO convertCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setDob(customer.getCustomerId().getDateOfBirth());
        customerDTO.setLastName(customer.getCustomerId().getLastName());
        customerDTO.setFirstName(customer.getFullName().split("\\s")[0]);
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setPhoneType(customer.getPhoneType().toString());

        if (customer.getCreditReport() != null) {
            CreditReport creditReport = customer.getCreditReport();
            CreditReportDTO creditReportDTO = new CreditReportDTO();
            creditReportDTO.setBureu(creditReport.getBureu());
            creditReportDTO.setCreditScrore(creditReport.getScrore());
            creditReportDTO.setReportDate(creditReport.getReportDate());
            customerDTO.setCreditReport(creditReportDTO);
        }
        if (customer.getLoanApplications() != null) {
            customerDTO.setLoanApplications(new ArrayList<>());
            customerDTO.getLoanApplications().addAll(customer.getLoanApplications());
        }
        return customerDTO;
    }

    public static CustomerDTO convertCustomer(String id, Customer customer) {
        CustomerDTO customerDTO = convertCustomer(customer);
        customerDTO.setId(id);
        return customerDTO;
    }

}
