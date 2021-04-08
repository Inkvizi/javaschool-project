package services.operation;

import java.math.BigDecimal;

public class BalanceCard implements Balance{
    private final BigDecimal amount;
    private final Currency currency;

    public BalanceCard(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
}
