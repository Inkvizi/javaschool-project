package bank.client;

import bank.card.Card;
import bank.services.operation.Balance;

public interface Client {
    String getName();
    String getCode();
    int getAge();
    boolean hasCard(Card card);
    boolean checkCardPin(Card sourceCard);
    boolean checkCardExpirationDate(Card sourceCard);
    Account getAccountByCard(Card card);
}
