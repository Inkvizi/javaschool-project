package github.marinets.atm.server.operation;

import github.marinets.atm.common.Currency;
import java.math.BigDecimal;

public class BalanceCardDebet extends BalanceCard {
    public BalanceCardDebet(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }
}
