package atm.balance;

import common.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceData {
    BigDecimal amount;
    Currency currency;
    String accountNumber;
}
