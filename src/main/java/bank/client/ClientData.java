package bank.client;

import bank.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import bank.services.operation.Balance;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class ClientData implements Client, Address {
    private String name;
    private String code;
    private int age;
    @Delegate
    private Address address;
    private List<Account> accounts;

    @Override
    public boolean hasCard(Card card) {
        return accounts.stream().filter(account -> account.hasCard(card)).count() == 1;
    }

    @Override
    public boolean checkCardPin(Card sourceCard) {
        return accounts.stream().filter(account -> account.checkCardPin(sourceCard)).count() == 1;
    }

    @Override
    public boolean checkCardExpirationDate(Card sourceCard) {
        return false;
    }

    @Override
    public Balance getBalance(Card card) {
        Optional<Account> accountOptional = accounts.stream().filter(account -> account.hasCard(card)).findAny();
        return accountOptional.get().getBalance();
    }
}
