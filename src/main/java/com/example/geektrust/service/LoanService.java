package com.example.geektrust.service;

import com.example.geektrust.exception.dto.BalanceDTO;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;
import com.example.geektrust.exception.LedgerCoException;
import com.example.geektrust.repository.ILoanRepository;

import static com.example.geektrust.helpers.Utils.*;

public class LoanService implements ILoanService {
    private final ILoanRepository loanRepository;

    public LoanService(ILoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan create(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public void makePayment(String key, LumpPayment lumpPayment) throws LedgerCoException {
        Loan loan = loanRepository.findByName(key).orElseThrow(() -> new LedgerCoException("Loan details not found!"));
        loanRepository.makePayment(loan, lumpPayment);
    }

    @Override
    public BalanceDTO getBalance(String key, Integer emiNo) throws LedgerCoException {
        if (emiNo < MINIMUM_VALID_EMI_NO) {
            throw new LedgerCoException("Invalid Emi Number, Try Again");
        }
        Loan loan = loanRepository.findByName(key).orElseThrow(() -> new LedgerCoException("Loan details not found!"));

        Double amountPayed = getAmountPayedTillEmiNo(emiNo, loan);
        Integer emiRemaining = getEmiRemaining(loan.getEmiPerMonth(), loan.getTotalAmount(), amountPayed);

        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setAmountPayed(amountPayed);
        balanceDTO.setEmiRemaining(emiRemaining);

        return balanceDTO;

    }
}
