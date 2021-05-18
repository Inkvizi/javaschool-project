package common.dto;

import common.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceDTO {
    BigDecimal amount;
    Currency currency;
    String accountNumber;
}
