package com.bddinaction.serenitybank.deposits;

import java.math.BigDecimal;
import java.util.function.Function;

public class BasicSaverDepositFee implements Function<BigDecimal, BigDecimal> {

    private static BigDecimal BASIC_SAVER_RATE = new BigDecimal("0.50");

    @Override
    public BigDecimal apply(BigDecimal amount) {
        return BASIC_SAVER_RATE;
    }
}
