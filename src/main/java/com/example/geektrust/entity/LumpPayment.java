package com.example.geektrust.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class LumpPayment {
    @Getter
    @Setter
    private Integer emiNo;
    @Getter
    @Setter
    private Double lumpAmount;

    public LumpPayment() {
        this.emiNo = null;
        this.lumpAmount = null;
    }

    public LumpPayment(Integer emiNo, Double lumpAmount) {
        this.lumpAmount = lumpAmount;
        this.emiNo = emiNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LumpPayment that = (LumpPayment) o;
        return Objects.equals(emiNo, that.emiNo) && Objects.equals(lumpAmount, that.lumpAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emiNo, lumpAmount);
    }

    @Override
    public String toString() {
        return "LumpPayment{" +
                "emiNo=" + emiNo +
                ", lumpAmount=" + lumpAmount +
                '}';
    }
}
