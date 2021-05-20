package github.marinets.atm.server.entity.client;

import github.marinets.atm.server.entity.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class ClientData implements Client, Address {
    private final String name;
    private final String code;
    private final int age;
    @Delegate
    private final Address address;
    private final List<Account> accounts;

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
        return sourceCard.CheckExpirationDate();
    }

    @Override
    public Account getAccountByCard(Card card) {
        Optional<Account> accountOptional = accounts.stream().filter(account -> account.hasCard(card)).findAny();
        return accountOptional.get();
    }
}
