package com.globant.poc.converter;

import java.util.ArrayList;

import com.globant.poc.dto.LoanDetailDTO;
import com.globant.poc.model.Customer;
import com.globant.poc.model.LoanApplication;
import com.globant.poc.model.LoanDetail;

public class LoanDetailConverter {

    public static LoanDetail convertToLoanDetail(Customer customer, LoanDetailDTO loanDetailDTO) {
        LoanDetail loanDetail = new LoanDetail();
        loanDetail.setInterestRate(loanDetailDTO.getInterestRate());
        loanDetail.setPrincipal(loanDetailDTO.getPrincipal());
        loanDetail.setLoanTerm(loanDetailDTO.getLoanTerm());
        if (loanDetailDTO.getLoanApplication() != null) {
            LoanApplication loanApplication = loanDetailDTO.getLoanApplication();
            loanApplication.setCustomer(customer);
            loanApplication.setLoanDetail(new ArrayList<>());
            loanApplication.getLoanDetail().add(loanDetail);
            loanDetail.setLoanApplication(loanApplication);
        }
        if (loanDetailDTO.getCollateral() != null) {
            loanDetailDTO.getCollateral().forEach(collateral -> {
                loanDetail.addCollateral(collateral);
            });
        }
        return loanDetail;
    }

    public static LoanDetailDTO convertToLoanDetailDTO(LoanDetail loanDetail) {
        LoanDetailDTO loanDetailDTO = new LoanDetailDTO();
        loanDetailDTO.setInterestRate(loanDetail.getInterestRate());
        loanDetailDTO.setPrincipal(loanDetail.getPrincipal());
        loanDetailDTO.setLoanTerm(loanDetail.getLoanTerm());
        loanDetailDTO.setLoanApplication(loanDetail.getLoanApplication());
        loanDetailDTO.setCollateral(loanDetail.getCollateral());
        return loanDetailDTO;
    }
}
