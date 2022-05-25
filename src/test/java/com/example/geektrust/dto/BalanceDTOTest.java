package com.example.geektrust.dto;

import com.example.geektrust.exception.dto.BalanceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceDTOTest {
    @Test
    public void shouldGenerateBalanceString() {
        BalanceDTO balanceDTO = new BalanceDTO(1000.0, 3);
        String expected = "1000 3";
        String actual = balanceDTO.toString();
        Assertions.assertEquals(expected, actual);

    }
}
