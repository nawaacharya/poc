package com.globant.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.poc.dto.LoanDetailDTO;
import com.globant.poc.service.impl.LoanDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/loan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "LoanController", description = "CRUD operations on loan details")
public class LoanController {

    @Autowired
    private LoanDetailService loanService;

    @GetMapping("/find/{lastName}")
    public List<LoanDetailDTO> finLoansByLastName(@PathVariable String lastName) {
        return loanService.getLoanApplicationByLastName(lastName);
    }

    @PutMapping("/add")
    public LoanDetailDTO addLoans(@RequestBody LoanDetailDTO loanDetailDTO) {
        return loanService.saveLoanDetail(loanDetailDTO);
    }

}
