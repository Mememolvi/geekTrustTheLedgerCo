package com.example.geektrust.command;

import com.example.geektrust.exception.dto.BalanceDTO;
import com.example.geektrust.service.ILoanService;

import java.util.List;

import static com.example.geektrust.helpers.Utils.*;

public class BalanceCommand implements ILoanCommand {
    private final ILoanService loanService;

    public BalanceCommand(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            String key = generateKey(commandParts.get(BANK_NAME_INDEX), commandParts.get(BORROWER_NAME_INDEX));
            Integer emiNumber = Integer.parseInt(commandParts.get(EMI_NO_INDEX));
            BalanceDTO balanceDTO = loanService.getBalance(key, emiNumber);
            System.out.println(generateBalanceString(commandParts, balanceDTO));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String generateBalanceString(List<String> commandParts, BalanceDTO balanceDTO) {
        return commandParts.get(BANK_NAME_INDEX) + " " + commandParts.get(BORROWER_NAME_INDEX) + " " + balanceDTO.toString();
    }
}
