package com.example.geektrust.helpers;

import com.example.geektrust.entity.Loan;

public class Utils {

    public static final int BANK_NAME_INDEX = 1;
    public static final int BORROWER_NAME_INDEX = 2;
    public static final int EMI_NO_INDEX = 3;

    public static final int PRINCIPAL_AMOUNT_INDEX = 3;
    public static final int NO_OF_YEARS_INDEX = 4;
    public static final int RATE_OF_INTEREST_INDEX = 5;
    public static final int LUMP_AMOUNT_INDEX = 3;
    public static final int EMI_NO_INDEX_FOR_PAYMENT = 4;
    public static final int MINIMUM_VALID_EMI_NO = 0;
    public static final int MONTHS_IN_YEAR = 12;
    public static final int EMI_SUBTRACTION = 1;


    public static int getEmiRemaining(double emiPerMonth, double totalAmount, double amountPayed) {
        return (int) Math.ceil((float) (totalAmount - amountPayed) / emiPerMonth);
    }

    public static Double getAmountPayedTillEmiNo(Integer emiNo, Loan loan) {
        Double emiPerMonth = loan.getEmiPerMonth();
        Double totalAmount = loan.getTotalAmount();
        Double lumpSumPayed = loan.getLumpPaymentsMadeTillDate(emiNo);

        if (emiNo == MINIMUM_VALID_EMI_NO) {
            return lumpSumPayed;
        }
        Double amountBeforeGivenEmi = ((emiNo - EMI_SUBTRACTION) * emiPerMonth) + lumpSumPayed;
        Double amountRemaining = totalAmount - amountBeforeGivenEmi;

        if (amountRemaining < emiPerMonth) {
            return amountBeforeGivenEmi + amountRemaining;
        }
        return ((emiNo) * emiPerMonth) + lumpSumPayed;
    }

    public static String generateKey(String bankName, String borrowerName) {
        return bankName + " : " + borrowerName;
    }

}
