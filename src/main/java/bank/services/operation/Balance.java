package bank.services.operation;

import common.Currency;

import java.math.BigDecimal;

public interface Balance {
    BigDecimal getAmount();
    void setAmount(BigDecimal amount);
    Currency getCurrency();
}
