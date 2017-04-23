package com.bddinaction.serenitybank.deposits;

import com.bddinaction.serenitybank.model.AccountType;
import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

import static com.bddinaction.serenitybank.model.AccountType.*;
import static java.math.BigDecimal.ZERO;

public class DepositFee {
    private final static Map<AccountType, Function<BigDecimal, BigDecimal>> DEPOSIT_FEES_BY_ACCOUNT_TYPE = ImmutableMap.of(
            Current,        depositAmount -> ZERO,
            BasicSavings,   new BasicSaverDepositFee(),
            BigSaver,       new BigSaverDepositFee()
    );

    public static Function<BigDecimal, BigDecimal> forAccountType(AccountType type) {
        return DEPOSIT_FEES_BY_ACCOUNT_TYPE.get(type);
    }
}
