package com.globant.poc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.poc.model.strategy.LoanModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Collateral implements LoanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double marketValue;

    @ManyToMany(mappedBy = "collateral")
    @JsonIgnore
    private List<LoanDetail> loanDetails;
}
