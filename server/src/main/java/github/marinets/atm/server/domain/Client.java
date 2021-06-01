package github.marinets.atm.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class Client {
    private final String name;
    private final String code;
    private final int age;
    private final Address address;
    private final List<Account> accounts;

    public boolean hasCard(Card card) {
        return accounts.stream().filter(account -> account.hasCard(card)).count() == 1;
    }

    public boolean checkCardPin(Card sourceCard) {
        return accounts.stream().filter(account -> account.checkCardPin(sourceCard)).count() == 1;
    }

    public boolean checkCardExpirationDate(Card sourceCard) {
        return sourceCard.CheckExpirationDate();
    }

    public Account getAccountByCard(Card card) {
        Optional<Account> accountOptional = accounts.stream().filter(account -> account.hasCard(card)).findAny();
        return accountOptional.get();
    }
}
