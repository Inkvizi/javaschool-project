package bank.services.operation;

import common.Currency;

import java.math.BigDecimal;

public class BalanceCardCredit extends BalanceCard{
    public BalanceCardCredit(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }
}
