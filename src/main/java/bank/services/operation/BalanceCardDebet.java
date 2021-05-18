package bank.services.operation;

import common.Currency;

import java.math.BigDecimal;

public class BalanceCardDebet extends BalanceCard {
    public BalanceCardDebet(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }
}
