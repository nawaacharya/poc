package com.globant.poc.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.poc.model.strategy.LoanModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LoanDetail implements LoanModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private Double principal;

    private Double interestRate;

    private Integer loanTerm;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( //creates a 3rd table and stores the FK for both tables
        name = "loanDetail_collaterals",
        joinColumns = @JoinColumn(name = "loan_id"), //foreign key to loanDetail
        inverseJoinColumns = @JoinColumn(name = "collateral_id") //foreign key to collateral
    )
    private List<Collateral> collateral;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    @JsonIgnore
    private LoanApplication loanApplication;


    public void addCollateral(Collateral collateral) {
        if (this.getCollateral() == null) {
            this.setCollateral(new ArrayList<>());
        }
        if (collateral.getLoanDetails() == null) {
            collateral.setLoanDetails(new ArrayList<>());
        }
        collateral.getLoanDetails().add(this);
        this.getCollateral().add(collateral);
    }
}