package com.example.geektrust.appconfig;

import com.example.geektrust.command.BalanceCommand;
import com.example.geektrust.command.CommandInvoker;
import com.example.geektrust.command.CreateLoanCommand;
import com.example.geektrust.command.MakePaymentCommand;
import com.example.geektrust.repository.ILoanRepository;
import com.example.geektrust.repository.LoanRepository;
import com.example.geektrust.service.ILoanService;
import com.example.geektrust.service.LoanService;

public class ApplicationConfig {

    static ApplicationConfig applicationConfig = new ApplicationConfig();

    private ApplicationConfig() {

    }

    public static ApplicationConfig getInstanceOfApplicationConfig() {
        return applicationConfig;
    }

    private final ILoanRepository loanRepository = new LoanRepository();
    private final ILoanService loanService = new LoanService(loanRepository);
    private final CreateLoanCommand createLoanCommand = new CreateLoanCommand(loanService);
    private final MakePaymentCommand makePaymentCommand = new MakePaymentCommand(loanService);
    private final BalanceCommand balanceCommand = new BalanceCommand(loanService);
    private final CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAN", createLoanCommand);
        commandInvoker.register("PAYMENT", makePaymentCommand);
        commandInvoker.register("BALANCE", balanceCommand);
        return commandInvoker;
    }
}
