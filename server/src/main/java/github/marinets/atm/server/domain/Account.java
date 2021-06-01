package github.marinets.atm.server.domain;

import github.marinets.atm.server.operation.Balance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
public class Account<T extends Balance> {
    @Getter
    private final String number;
    @Getter private final T balance;
    private List<Card> cards;

    public boolean hasCard(Card card) {
        return cards.stream().filter(
                cardClient -> cardClient.getNumber().equals(card.getNumber())).count() == 1;
    }
    public boolean checkCardPin(Card card) {
        return cards.stream().filter(cardClient -> (
                cardClient.getNumber().equals(card.getNumber()) && cardClient.CheckPinCode(card.getPinCode()))).count() == 1;
    }

    public boolean checkCardExpirationDate(Card card) {
        return card.CheckExpirationDate();
    }
}
