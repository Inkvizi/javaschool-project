package github.marinets.atm.server.operation;

import github.marinets.atm.common.Currency;
import java.math.BigDecimal;

public class BalanceCardCredit extends BalanceCard{
    public BalanceCardCredit(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }
}
