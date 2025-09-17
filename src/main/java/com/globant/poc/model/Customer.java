package com.globant.poc.model;

import java.util.ArrayList;
import java.util.List;

import com.globant.poc.enums.PhoneType;
import com.globant.poc.model.embeddedIds.CustomerId;
import com.globant.poc.model.strategy.LoanModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findByLastName",
                query = "SELECT c FROM Customer c WHERE c.customerId.lastName = :lastName")
})
public class Customer implements LoanModel {

    @Transient
    private String id;

    @EmbeddedId //composite keys
    private CustomerId customerId;

    private String fullName;

    private String email;

    @Enumerated(EnumType.STRING) //enums require separate annotation for mapping, generates column in same table.
    private PhoneType phoneType;

    private String phoneNumber;

    @OneToOne (cascade = CascadeType.ALL) //if customer is saved/deleted, save creditReport -> cascade
    @JoinColumn(name = "cr_id", referencedColumnName = "id")
    private CreditReport creditReport;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanApplication> loanApplications;

    //helper method to add loanApplication
    public void addLoanApplication(LoanApplication loanApplication) {
        if (this.loanApplications == null) {
            this.loanApplications = new ArrayList<>();
        }
        loanApplication.setCustomer(this);
        this.loanApplications.add(loanApplication);
    }
}
