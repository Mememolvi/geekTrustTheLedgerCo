package com.example.geektrust.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class BalanceDTO {
    @Getter
    @Setter
    private Double amountPayed;
    @Getter
    @Setter
    private Integer emiRemaining;

    public BalanceDTO() {
    }

    public BalanceDTO(Double amountPayed, Integer emiRemaining) {
        this.amountPayed = amountPayed;
        this.emiRemaining = emiRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDTO that = (BalanceDTO) o;
        return Objects.equals(amountPayed, that.amountPayed) && Objects.equals(emiRemaining, that.emiRemaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountPayed, emiRemaining);
    }

    @Override
    public String toString() {
        return this.amountPayed.intValue() + " " + this.emiRemaining.intValue();
    }
}
