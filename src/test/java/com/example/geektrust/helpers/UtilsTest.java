package com.example.geektrust.helpers;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UtilsTest {

    @ParameterizedTest
    @MethodSource("remainingEmiValueSource")
    public void getEmiRemainingTest(double emiPerMonth, double totalAmount, double amountPayed, int expectedRemainingEmi) {
        int actualRemainingEmi = Utils.getEmiRemaining(emiPerMonth, totalAmount, amountPayed);
        Assertions.assertEquals(expectedRemainingEmi, actualRemainingEmi);

    }

    @ParameterizedTest
    @MethodSource("getAmountPayedValueSource")
    public void getAmountPayedTillEmiNo(Integer emiNo, Loan loan, double expectedAmountPayed) {
        double actualAmountRemaining = Utils.getAmountPayedTillEmiNo(emiNo, loan);
        Assertions.assertEquals(expectedAmountPayed, actualAmountRemaining);

    }

    @Test
    public void generateKeyTest() {
        String bankName = "IDID";
        String borrowerName = "Dale";
        String expectedKey = "IDID" + " : " + "Dale";
        String actualKey = Utils.generateKey(bankName, borrowerName);
        Assertions.assertEquals(expectedKey, actualKey);
    }

    private static Stream<Arguments> getAmountPayedValueSource() {
        return Stream.of(
                Arguments.of(24, new Loan("IDID", "Dale", 10000.0, 2, 10F, getSamplePaymentList()), 12000.0),
                Arguments.of(0, new Loan("IDID", "Dale", 10000.0, 5, 4F, null), 0.0),
                Arguments.of(3, new Loan("KDSC", "Dale", 20000.0, 2, 5F, null), 2751.0),
                Arguments.of(3, new Loan("IDID", "Dale", 20000.0, 2, 5F, getSamplePaymentList()), 2951.0),
                Arguments.of(0, new Loan("MADE", "Nate", 300000.0, 3, 5F, getSamplePaymentList()), 100.0)
        );
    }

    private static Stream<Arguments> remainingEmiValueSource() {
        return Stream.of(
                Arguments.of(33.0, 1000.0, 100.0, 28),
                Arguments.of(400, 20000.0, 20000.0, 0),
                Arguments.of(600, 45000.0, 30000.0, 25)
        );
    }

    private static List<LumpPayment> getSamplePaymentList() {
        List<LumpPayment> paymentList = new ArrayList<>();
        paymentList.add(new LumpPayment(0, 100.0));
        paymentList.add(new LumpPayment(2, 100.0));
        paymentList.add(new LumpPayment(4, 1000.0));
        paymentList.add(new LumpPayment(5, 120.0));
        return paymentList;
    }
}
