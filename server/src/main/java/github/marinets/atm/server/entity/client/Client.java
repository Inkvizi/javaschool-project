package github.marinets.atm.server.entity.client;

import github.marinets.atm.server.entity.card.Card;

public interface Client {
    String getName();
    String getCode();
    int getAge();
    boolean hasCard(Card card);
    boolean checkCardPin(Card sourceCard);
    boolean checkCardExpirationDate(Card sourceCard);
    Account getAccountByCard(Card card);
}
