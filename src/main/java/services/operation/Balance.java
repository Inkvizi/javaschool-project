package services.operation;

import java.math.BigDecimal;

public interface Balance {
    BigDecimal getAmount();
    Currency getCurrency();
}
