package com.example.geektrust.repository;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;

import java.util.Optional;

public interface ILoanRepository {
    Loan save(Loan loan);

    Optional<Loan> findByName(String key);

    void makePayment(Loan loan, LumpPayment lumpPayment);
}
