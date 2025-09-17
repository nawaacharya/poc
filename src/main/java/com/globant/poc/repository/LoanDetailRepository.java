package com.globant.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.globant.poc.model.LoanDetail;

import org.springframework.stereotype.Repository;

@Repository
public interface LoanDetailRepository extends JpaRepository<LoanDetail, Long> {

    @Query("SELECT ld FROM LoanDetail ld where ld.loanApplication.customer.customerId.lastName = :lastName")
    List<LoanDetail> getLoanDetailsByLastName(@Param("lastName") String lastName);
}
