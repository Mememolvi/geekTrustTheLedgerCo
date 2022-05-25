package com.example.geektrust.repository;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.LumpPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.example.geektrust.helpers.Utils.generateKey;

public class LoanRepositoryTest {
    private LoanRepository loanRepository;

    @BeforeEach
    void setup() {
        final Map<String, Loan> loanMap = new HashMap<String, Loan>() {
            {
                put(generateKey("IDID", "Dale"),
                        new Loan("IDID", "Dale", 10000.0, 5, 4F, null));
                put(generateKey("HDFE", "Shawn"),
                        new Loan("HDFE", "Shawn", 2000.0, 1, 2F, null));
                put(generateKey("STI", "John"),
                        new Loan("STI", "John", 35400.0, 3, 7F, getSamplePaymentList()));
                put(generateKey("JKB", "Nori"),
                        new Loan("JKB", "Nori", 90000.0, 8, 2F, getSamplePaymentList()));
            }
        };
        loanRepository = new LoanRepository(loanMap);
    }

    private static List<LumpPayment> getSamplePaymentList() {
        List<LumpPayment> paymentList = new ArrayList<>();
        paymentList.add(new LumpPayment(2, 100.0));
        paymentList.add(new LumpPayment(3, 1000.0));
        paymentList.add(new LumpPayment(4, 10000.0));
        return paymentList;
    }

    @Test
    public void shouldSaveLoanTest() {
        Loan entity = new Loan("JPM", "Nadal", 2500.0, 4, 4F, null);
        Loan expected = new Loan("JPM", "Nadal", 2500.0, 4, 4F, null);
        Loan actualEntity = loanRepository.save(entity);
        Assertions.assertEquals(expected, actualEntity);

    }

    @Test
    public void shouldFindLoanByNameTest() {
        Loan expected = new Loan("IDID", "Dale", 10000.0, 5, 4F, null);
        Loan actual = loanRepository.findByName(generateKey("IDID", "Dale")).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByNameWhenNotPresentTest() {
        Optional<Loan> actual = loanRepository.findByName(generateKey("IDI", "Dale"));
        Assertions.assertEquals(Optional.empty(), actual);
    }

    @Test
    public void shouldMakePaymentWhenPaymentListEmptyTest() {
        Loan loan = loanRepository.findByName(generateKey("IDID", "Dale")).get();
        LumpPayment lumpPayment = new LumpPayment(5, 1000.0);
        loanRepository.makePayment(loan, lumpPayment);
        List<LumpPayment> expected = new ArrayList<>();
        expected.add(lumpPayment);
        List<LumpPayment> actual = loan.getLumpPaymentList();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldMakePaymentWhenPaymentListPresentTest() {
        Loan loan = loanRepository.findByName(generateKey("STI", "John")).get();
        LumpPayment lumpPayment = new LumpPayment(5, 1000.0);
        loanRepository.makePayment(loan, lumpPayment);
        List<LumpPayment> expected = getSamplePaymentList();
        expected.add(lumpPayment);
        List<LumpPayment> actual = loan.getLumpPaymentList();
        Assertions.assertEquals(expected, actual);

    }

}
