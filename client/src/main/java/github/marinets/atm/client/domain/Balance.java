package github.marinets.atm.client.domain;

import github.marinets.atm.common.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balance {
    BigDecimal amount;
    Currency currency;
    String accountNumber;
}
