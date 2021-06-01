package github.marinets.atm.client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    String number;
    LocalDate expirationDate;
    String pinCode;
}
