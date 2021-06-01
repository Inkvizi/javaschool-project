package github.marinets.atm.server.operation;

import github.marinets.atm.common.Currency;
import java.math.BigDecimal;

public interface Balance {
    BigDecimal getAmount();
    void setAmount(BigDecimal amount);
    Currency getCurrency();
}
