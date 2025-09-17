package com.globant.poc.dto;

import java.util.List;

import com.globant.poc.model.Collateral;
import com.globant.poc.model.LoanApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetailDTO {
    private Double principal;
    private Double interestRate;
    private Integer loanTerm;
    private CustomerDTO customer;
    private List<Collateral> collateral;
    private LoanApplication loanApplication;
}
