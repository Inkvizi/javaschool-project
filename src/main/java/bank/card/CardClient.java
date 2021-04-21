package bank.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CardClient implements Card {
    private String number;
    private String pinCode;
    private LocalDate expirationDate;

    @Override
    public boolean CheckPinCode() {
        return pinCode.length() == 3;
    }

    @Override
    public boolean CheckExpirationDate() {
        return (expirationDate.isAfter(LocalDate.now()) || (expirationDate.isEqual(LocalDate.now())));
    }
}
