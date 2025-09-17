package com.globant.poc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globant.poc.converter.LoanDetailConverter;
import com.globant.poc.dto.LoanDetailDTO;
import com.globant.poc.model.Customer;
import com.globant.poc.model.LoanDetail;
import com.globant.poc.model.embeddedIds.CustomerId;
import com.globant.poc.repository.LoanDetailRepository;
import com.globant.poc.service.LoanDetailStrategy;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class LoanDetailService implements LoanDetailStrategy{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanDetailRepository repository;

    @Override
    public List<LoanDetailDTO> getLoanApplicationByLastName(String lastName) {
        List<LoanDetail> loanDetails = repository.getLoanDetailsByLastName(lastName);
        return loanDetails.stream().map(LoanDetailConverter::convertToLoanDetailDTO).toList();
    }

    @Override
    public LoanDetailDTO saveLoanDetail(LoanDetailDTO loanDetailDTO) {
        Customer customer = customerService.getCustomer(
            new CustomerId(loanDetailDTO.getCustomer().getLastName(), loanDetailDTO.getCustomer().getDob()));
        LoanDetail loanDetail = LoanDetailConverter.convertToLoanDetail(customer, loanDetailDTO);
        return LoanDetailConverter.convertToLoanDetailDTO(repository.save(loanDetail));
    }

}
