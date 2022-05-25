package com.example.geektrust.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.example.geektrust.helpers.Utils.MONTHS_IN_YEAR;

public class Loan {
    @Getter
    @Setter
    private String bankName;
    @Getter
    @Setter
    private String borrowerName;
    @Getter
    @Setter
    private Double principalAmount;
    @Getter
    @Setter
    private Integer noOfYears;
    @Getter
    @Setter
    private Float rateOfInterest;
    @Getter
    @Setter
    private List<LumpPayment> lumpPaymentList;

    public Loan() {
    }

    public Loan(String bankName, String borrowerName, Double principalAmount, Integer noOfYears, Float rateOfInterest, List<LumpPayment> lumpPaymentList) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.noOfYears = noOfYears;
        this.rateOfInterest = rateOfInterest;
        this.lumpPaymentList = lumpPaymentList;
    }

    public Double getTotalAmount() {
        return this.principalAmount + getInterest();
    }

    private double getInterest() {
        return (this.principalAmount * this.noOfYears * this.rateOfInterest) / 100;
    }

    public Double getEmiPerMonth() {
        return Math.ceil(this.getTotalAmount() / (this.getNoOfYears() * MONTHS_IN_YEAR));
    }

    public Double getLumpPaymentsMadeTillDate(Integer emiNo) {
        if (this.getLumpPaymentList() == null) {
            return 0.0;
        }
        return this.getLumpPaymentList().stream().filter(obj -> obj.getEmiNo() <= emiNo).map(o -> o.getLumpAmount()).reduce(0.0, Double::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(bankName, loan.bankName) && Objects.equals(borrowerName, loan.borrowerName) && Objects.equals(principalAmount, loan.principalAmount) && Objects.equals(noOfYears, loan.noOfYears) && Objects.equals(rateOfInterest, loan.rateOfInterest) && Objects.equals(lumpPaymentList, loan.lumpPaymentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName, principalAmount, noOfYears, rateOfInterest, lumpPaymentList);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "bankName='" + bankName + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", principalAmount=" + principalAmount +
                ", noOfYears=" + noOfYears +
                ", rateOfInterest=" + rateOfInterest +
                ", lumpPaymentList=" + lumpPaymentList +
                '}';
    }
}
