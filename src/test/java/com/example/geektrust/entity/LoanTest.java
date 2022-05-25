package com.example.geektrust.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LoanTest {

    @ParameterizedTest
    @MethodSource("loanAmountInputSource")
    public void shouldGetTotalAmountForLoan(Loan loan, Double expectedOutput) {
        Double actualOutput = loan.getTotalAmount();
        Assertions.assertEquals(actualOutput, expectedOutput);
    }

    @ParameterizedTest
    @MethodSource("loanEmiInputSource")
    public void shouldGetEmiPerMonthFromLoan(Loan loan, Double expectedOutput) {
        Double actualOutput = loan.getEmiPerMonth();
        Assertions.assertEquals(actualOutput, expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({"1,100.0", "2,1100.0", "3,1300.0", "4,1300.0", "6,1600.0", "10,11600.0"})
    public void shouldReturnSumOfLumpPaymentMadeTillEmiNo(Integer emiNo, Double expectedOutput) {
        List<LumpPayment> lumpPaymentList = new ArrayList<>();
        lumpPaymentList.add(new LumpPayment(1, 100.0));
        lumpPaymentList.add(new LumpPayment(2, 1000.0));
        lumpPaymentList.add(new LumpPayment(3, 200.0));
        lumpPaymentList.add(new LumpPayment(6, 300.0));
        lumpPaymentList.add(new LumpPayment(10, 10000.0));


        Loan loan = new Loan("ABC", "Jack", 300000.0, 10, 15F, lumpPaymentList);
        Double actualOutput = loan.getLumpPaymentsMadeTillDate(emiNo);
        Assertions.assertEquals(actualOutput, expectedOutput);
    }

    private static Stream<Arguments> loanAmountInputSource() {
        return Stream.of(
                Arguments.of(new Loan("IDID", "Dale", 10000.0, 5, 4F, null), 12000.0),
                Arguments.of(new Loan("MBI", "John", 1200.0, 2, 2F, null), 1248.0),
                Arguments.of(new Loan("ABC", "Jack", 300000.0, 10, 15F, getSamplePaymentList()), 750000.0)
        );
    }

    private static Stream<Arguments> loanEmiInputSource() {
        return Stream.of(
                Arguments.of(new Loan("KODC", "Nick", 20300.0, 3, 1F, null), 581.0),
                Arguments.of(new Loan("JONS", "Kendrick", 4000.0, 2, 2F, null), 174.0),
                Arguments.of(new Loan("GOLD", "Lamar", 12300.0, 1, 35F, getSamplePaymentList()), 1384.0)
        );
    }

    private static List<LumpPayment> getSamplePaymentList() {
        List<LumpPayment> paymentList = new ArrayList<>();
        paymentList.add(new LumpPayment(2, 100.0));
        paymentList.add(new LumpPayment(3, 1000.0));
        paymentList.add(new LumpPayment(4, 10000.0));
        return paymentList;
    }

}
