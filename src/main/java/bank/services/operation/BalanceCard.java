package bank.services.operation;

import common.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class BalanceCard implements Balance{
    @Getter @Setter private BigDecimal amount;
    @Getter private final Currency currency;

    public BalanceCard(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
