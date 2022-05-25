package com.example.geektrust.command;

import com.example.geektrust.entity.LumpPayment;
import com.example.geektrust.service.ILoanService;

import java.util.List;

import static com.example.geektrust.helpers.Utils.*;

public class MakePaymentCommand implements ILoanCommand {
    private final ILoanService loanService;

    public MakePaymentCommand(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            String bankName = commandParts.get(BANK_NAME_INDEX);
            String borrowerName = commandParts.get(BORROWER_NAME_INDEX);
            String key = generateKey(bankName, borrowerName);
            Double lumpAmount = Double.parseDouble(commandParts.get(LUMP_AMOUNT_INDEX));
            Integer emiNo = Integer.parseInt(commandParts.get(EMI_NO_INDEX_FOR_PAYMENT));
            LumpPayment lumpPayment = new LumpPayment(emiNo, lumpAmount);
            loanService.makePayment(key, lumpPayment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
