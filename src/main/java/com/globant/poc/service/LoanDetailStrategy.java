package com.globant.poc.service;

import java.util.List;

import com.globant.poc.dto.LoanDetailDTO;

public interface LoanDetailStrategy {
    
    List<LoanDetailDTO> getLoanApplicationByLastName(String lastName);

    LoanDetailDTO saveLoanDetail(LoanDetailDTO loanDetail);

}
