package com.bddinaction.serenitybank.deposits;

import java.math.BigDecimal;
import java.util.function.Function;

public class BigSaverDepositFee implements Function<BigDecimal, BigDecimal> {

    private static BigDecimal BIG_SAVER_RATE = new BigDecimal("0.50");

    @Override
    public BigDecimal apply(BigDecimal amount) {
        return BIG_SAVER_RATE;
    }
}
