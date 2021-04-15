package bank.card;

import bank.card.Card;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
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
