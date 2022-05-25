package com.example.geektrust.service;

import com.example.geektrust.exception.dto.BalanceDTO;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;
import com.example.geektrust.exception.LedgerCoException;
import com.example.geektrust.repository.ILoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.geektrust.helpers.Utils.generateKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("LoanServiceTest")
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {


    @Mock
    private ILoanRepository loanRepositoryMock;

    @InjectMocks
    private LoanService loanService;


    @ParameterizedTest
    @MethodSource("balanceSource")
    public void shouldGetBalanceTest(int emiNo, BalanceDTO balanceDTOExpected) throws LedgerCoException {
        Loan loan = new Loan("JPM", "Nadal", 2500.0, 4, 4F, getSamplePaymentList());
        when(loanRepositoryMock.findByName(anyString())).thenReturn(Optional.of(loan));
        BalanceDTO balanceDTOActual = loanService.getBalance(generateKey("JPM", "Nadal"), emiNo);
        assertEquals(balanceDTOExpected, balanceDTOActual);
        verify(loanRepositoryMock, times(1)).findByName(anyString());
    }

    @Test
    public void shouldMakePaymentTest() throws LedgerCoException {
        ArgumentCaptor<Loan> loanCapture = ArgumentCaptor.forClass(Loan.class);
        ArgumentCaptor<LumpPayment> lumpPaymentCapture = ArgumentCaptor.forClass(LumpPayment.class);
        Loan loan = new Loan("JPM", "Nadal", 25000.0, 4, 4F, null);
        when(loanRepositoryMock.findByName(anyString())).thenReturn(Optional.of(loan));
        doNothing().when(loanRepositoryMock).makePayment(loanCapture.capture(), lumpPaymentCapture.capture());
        LumpPayment lumpPayment = new LumpPayment(2, 150.0);
        loanService.makePayment(generateKey("JPM", "Nadal"), lumpPayment);
        assertEquals(loan, loanCapture.getValue());
        assertEquals(lumpPayment, lumpPaymentCapture.getValue());
        verify(loanRepositoryMock, times(1)).makePayment(loan, lumpPayment);
    }

    @Test
    public void shouldFailToMakePaymentWhenLoanDetailsNotFoundTest() {
        when(loanRepositoryMock.findByName(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(LedgerCoException.class, () -> loanService.makePayment("JPM", null));
    }

    @Test
    public void shouldFailToGetBalanceWhenEmiNoInvalidTest() {
        Assertions.assertThrows(LedgerCoException.class, () -> loanService.getBalance("JPM", -1));
    }

    @Test
    public void shouldFailToGetBalanceWhenLoanDetailsInvalidTest() {
        Assertions.assertThrows(LedgerCoException.class, () -> loanService.getBalance("JPM", 2));
    }

    private static Stream<Arguments> balanceSource() {
        return Stream.of(
                Arguments.of(1, generateBalanceDTO(61.0, 47)),
                Arguments.of(2, generateBalanceDTO(222.0, 44)),
                Arguments.of(0, generateBalanceDTO(0.0, 48)),
                Arguments.of(6, generateBalanceDTO(1966.0, 16))
        );
    }

    private static BalanceDTO generateBalanceDTO(Double amountPayed, int emiRemaining) {
        return new BalanceDTO(amountPayed, emiRemaining);
    }

    private static List<LumpPayment> getSamplePaymentList() {
        List<LumpPayment> paymentList = new ArrayList<>();
        paymentList.add(new LumpPayment(2, 100.0));
        paymentList.add(new LumpPayment(3, 1000.0));
        paymentList.add(new LumpPayment(4, 500.0));
        return paymentList;
    }

}
