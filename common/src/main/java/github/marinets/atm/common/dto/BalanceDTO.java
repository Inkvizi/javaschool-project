package github.marinets.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import github.marinets.atm.common.Currency;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceDTO {
    BigDecimal amount;
    Currency currency;
    String accountNumber;
}
