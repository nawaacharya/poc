package com.globant.poc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.poc.enums.LoanStatus;
import com.globant.poc.enums.LoanType;
import com.globant.poc.model.strategy.LoanModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LoanApplication implements LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "fk_lastName", referencedColumnName = "lastName"),
        @JoinColumn(name = "fk_dateOfBirth", referencedColumnName = "dateOfBirth")
    })
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "loanApplication")
    private List<LoanDetail> loanDetail;

}
