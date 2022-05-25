package com.example.geektrust.commands;

import com.example.geektrust.command.CreateLoanCommand;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.service.LoanService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CreateLoanCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreateLoanCommandTest {
    @Mock
    LoanService loanServiceMock;

    @InjectMocks
    CreateLoanCommand createLoanCommand;

    @Test
    public void shouldCreateLoanTest() {
        Loan loan = new Loan("JPM", "Nadal", 25000.0, 4, 4F, null);
        when(loanServiceMock.create(any(Loan.class))).thenReturn(loan);
        List<String> list = new ArrayList<>();
        list.add("LOAN");
        list.add("JPM");
        list.add("Nadal");
        list.add("25000");
        list.add("4");
        list.add("4");
        createLoanCommand.execute(list);
        verify(loanServiceMock, times(1)).create(loan);
    }


}
