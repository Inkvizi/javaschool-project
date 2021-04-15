package bank.client;

import bank.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Account<Balance> {
    @Getter private String number;
    @Getter private bank.services.operation.Balance balance;
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
