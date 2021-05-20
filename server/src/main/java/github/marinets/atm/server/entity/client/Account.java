package github.marinets.atm.server.entity.client;

import github.marinets.atm.server.operation.Balance;
import github.marinets.atm.server.entity.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Account<T extends Balance> {
    @Getter private final String number;
    @Getter private final T balance;
    private List<Card> cards;

    public boolean hasCard(Card card) {
        return cards.stream().filter(
                cardClient -> cardClient.getNumber() == card.getNumber()).count() == 1;
    }
    public boolean checkCardPin(Card card) {
        return cards.stream().filter(cardClient -> (
                cardClient.getNumber() == card.getNumber()) && cardClient.getPinCode() == card.getPinCode()).count() == 1;
    }

    public boolean checkCardExpirationDate(Card card) {
        return card.CheckExpirationDate();
    }
}
