package com.example.geektrust.commands;

import com.example.geektrust.command.MakePaymentCommand;
import com.example.geektrust.entity.LumpPayment;
import com.example.geektrust.exception.LedgerCoException;
import com.example.geektrust.service.LoanService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
class makePaymentCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Mock
    LoanService loanServiceMock;

    @InjectMocks
    MakePaymentCommand makePaymentCommand;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void paymentShouldFailWhenLoanDetailsNotFound() throws LedgerCoException {
        LumpPayment lumpPayment = new LumpPayment(4, 250.0);
        String expected = "Loan details not found!";
        doThrow(new LedgerCoException(expected)).when(loanServiceMock).makePayment("JPM : Nadal", lumpPayment);
        List<String> list = new ArrayList<>();
        list.add("PAYMENT");
        list.add("JPM");
        list.add("Nadal");
        list.add("250");
        list.add("4");
        makePaymentCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
        verify(loanServiceMock, times(1)).makePayment("JPM : Nadal", lumpPayment);
    }

    @Test
    public void shouldMakePaymentTest() throws LedgerCoException {
        ArgumentCaptor<String> keyCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<LumpPayment> lumpPaymentCapture = ArgumentCaptor.forClass(LumpPayment.class);
        doNothing().when(loanServiceMock).makePayment(keyCapture.capture(), lumpPaymentCapture.capture());
        String key = "JPM : Nadal";
        LumpPayment lumpPayment = new LumpPayment(4, 250.0);
        List<String> list = new ArrayList<>();
        list.add("PAYMENT");
        list.add("JPM");
        list.add("Nadal");
        list.add("250");
        list.add("4");
        makePaymentCommand.execute(list);
        verify(loanServiceMock, times(1)).makePayment(key, lumpPayment);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
