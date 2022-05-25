package com.example.geektrust.commands;

import com.example.geektrust.command.BalanceCommand;
import com.example.geektrust.exception.dto.BalanceDTO;
import com.example.geektrust.exception.LedgerCoException;
import com.example.geektrust.service.LoanService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("CreateLoanCommandTest")
@ExtendWith(MockitoExtension.class)
public class BalanceCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Mock
    LoanService loanServiceMock;

    @InjectMocks
    BalanceCommand balanceCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldFailToReadBalanceForInvalidEmiNo() throws LedgerCoException {
        String key = "JPM : Nadal";
        int emiNo = 4;
        String expected = "Invalid Emi Number, Try Again";
        doThrow(new LedgerCoException(expected)).when(loanServiceMock).getBalance(key, emiNo);
        List<String> list = new ArrayList<>();
        list.add("BALANCE");
        list.add("JPM");
        list.add("Nadal");
        list.add("4");
        balanceCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
        verify(loanServiceMock, times(1)).getBalance(key, 4);
    }

    @Test
    public void shouldFailToReadBalanceForInvalidLoanDetails() throws LedgerCoException {
        String key = "JPM : Nadal";
        int emiNo = 4;
        String expected = "Loan details not found!";
        doThrow(new LedgerCoException(expected)).when(loanServiceMock).getBalance(key, emiNo);
        List<String> list = new ArrayList<>();
        list.add("BALANCE");
        list.add("JPM");
        list.add("Nadal");
        list.add("4");
        balanceCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
        verify(loanServiceMock, times(1)).getBalance(key, 4);
    }

    @Test
    public void shouldPrintBalanceForValidInputTest() throws LedgerCoException {
        String key = "JPM : Nadal";
        int emiNo = 4;
        String expected = "JPM Nadal 1000 2";
        BalanceDTO balanceDTO = new BalanceDTO(1000.0, 2);
        when(loanServiceMock.getBalance(anyString(), anyInt())).thenReturn(balanceDTO);
        List<String> list = new ArrayList<>();
        list.add("BALANCE");
        list.add("JPM");
        list.add("Nadal");
        list.add("4");
        balanceCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
        verify(loanServiceMock, times(1)).getBalance(key, emiNo);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
