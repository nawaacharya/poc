package com.globant.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.poc.model.strategy.LoanModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_report_table")
public class CreditReport implements LoanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bureu;

    @Column(name = "credit_score")
    private Integer scrore;

    private String reportDate;

    @OneToOne(mappedBy =  "creditReport")
    @JsonIgnore
    private Customer customer;
}
