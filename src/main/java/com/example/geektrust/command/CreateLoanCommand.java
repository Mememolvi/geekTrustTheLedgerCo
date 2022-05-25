package com.example.geektrust.command;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.service.ILoanService;

import java.util.List;

import static com.example.geektrust.helpers.Utils.*;

public class CreateLoanCommand implements ILoanCommand {
    private final ILoanService loanService;

    public CreateLoanCommand(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            Loan loan = new Loan();
            loan.setBankName(commandParts.get(BANK_NAME_INDEX));
            loan.setBorrowerName(commandParts.get(BORROWER_NAME_INDEX));
            loan.setPrincipalAmount(Double.parseDouble(commandParts.get(PRINCIPAL_AMOUNT_INDEX)));
            loan.setNoOfYears(Integer.parseInt(commandParts.get(NO_OF_YEARS_INDEX)));
            loan.setRateOfInterest(Float.parseFloat(commandParts.get(RATE_OF_INTEREST_INDEX)));
            loanService.create(loan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
