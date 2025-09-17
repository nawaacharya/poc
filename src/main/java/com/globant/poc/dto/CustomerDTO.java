package com.globant.poc.dto;

import java.io.Serializable;
import java.util.List;

import com.globant.poc.model.LoanApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable{
    private String id;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String phoneNumber;
    private String phoneType;
    private CreditReportDTO creditReport;
    private List<LoanApplication> loanApplications;
}
