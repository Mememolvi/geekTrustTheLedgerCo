package com.example.geektrust.repository;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.geektrust.helpers.Utils.generateKey;

public class LoanRepository implements ILoanRepository {
    private final Map<String, Loan> loanMap;

    public LoanRepository() {
        loanMap = new HashMap<>();
    }

    public LoanRepository(Map<String, Loan> loanMap) {
        this.loanMap = loanMap;
    }

    @Override
    public Loan save(Loan loan) {
        String key = generateKey(loan.getBankName(), loan.getBorrowerName());
        loanMap.put(key, loan);
        return loan;
    }

    @Override
    public Optional<Loan> findByName(String key) {
        return Optional.ofNullable(loanMap.get(key));
    }

    @Override
    public void makePayment(Loan loan, LumpPayment lumpPayment) {
        if (loan.getLumpPaymentList() == null) {
            loan.setLumpPaymentList(new ArrayList<>());
            loan.getLumpPaymentList().add(lumpPayment);
        } else {
            loan.getLumpPaymentList().add(lumpPayment);
        }
    }
}
