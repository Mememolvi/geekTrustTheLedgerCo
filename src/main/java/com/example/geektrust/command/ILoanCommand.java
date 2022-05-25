package com.example.geektrust.command;

import java.util.List;

public interface ILoanCommand {
    void execute(List<String> commandParts);
}
