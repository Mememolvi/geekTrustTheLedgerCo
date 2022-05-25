package com.example.geektrust.command;

import com.example.geektrust.exception.LedgerCoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    static CommandInvoker commandInvoker = new CommandInvoker();

    private CommandInvoker() {

    }

    public static CommandInvoker getInstanceOfCommandInvoker() {
        return commandInvoker;
    }

    private static final Map<String, ILoanCommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ILoanCommand command) {
        commandMap.put(commandName, command);
    }

    private ILoanCommand get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws LedgerCoException {
        ILoanCommand command = get(commandName);
        if (command == null) {
            // Handle Exception
            throw new LedgerCoException("Invalid Command! Try Again");
        }
        command.execute(tokens);
    }

}
