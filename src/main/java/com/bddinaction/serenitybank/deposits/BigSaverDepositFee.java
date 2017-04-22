package com.bddinaction.serenitybank.deposits;

import java.math.BigDecimal;
import java.util.function.Function;

public class BigSaverDepositFee implements Function<BigDecimal, BigDecimal> {

    private static BigDecimal SMALL_SAVER_RATE = new BigDecimal("0.50");
    private static BigDecimal MEDIUM_SAVER_RATE = new BigDecimal("0.75");

    @Override
    public BigDecimal apply(BigDecimal amount) {
        if (isSmallDeposit(amount)) {
            return SMALL_SAVER_RATE;
        }
        return MEDIUM_SAVER_RATE;
    }

    private boolean isSmallDeposit(BigDecimal amount) {
        return amount.compareTo(new BigDecimal("100.00")) <= 0;
    }
}
