package com.example.geektrust.service;

import com.example.geektrust.exception.dto.BalanceDTO;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;
import com.example.geektrust.exception.LedgerCoException;

public interface ILoanService {
    Loan create(Loan loan);

    void makePayment(String key, LumpPayment lumpPayment) throws LedgerCoException;

    BalanceDTO getBalance(String key, Integer emiNo) throws LedgerCoException;
}
