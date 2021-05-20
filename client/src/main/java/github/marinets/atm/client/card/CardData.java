package github.marinets.atm.client.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardData {
    String number;
    LocalDate expirationDate;
    String pinCode;
}
