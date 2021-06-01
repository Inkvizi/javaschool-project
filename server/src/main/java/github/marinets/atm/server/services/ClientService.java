package github.marinets.atm.server.services;

import github.marinets.atm.common.Currency;
import github.marinets.atm.server.domain.Account;
import github.marinets.atm.server.domain.Address;
import github.marinets.atm.server.domain.Card;
import github.marinets.atm.server.domain.Client;
import github.marinets.atm.server.exception.ClientNotFoundException;
import github.marinets.atm.server.operation.Balance;
import github.marinets.atm.server.operation.BalanceCard;
import github.marinets.atm.server.repository.AccountCrudRepository;
import github.marinets.atm.server.repository.ClientCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    ClientCrudRepository clientCrudRepository;
    AccountService accountService;

    public Client getClientByCard(Card card) {
        Long accountId = accountService.getAccountIDByCard(card);
        github.marinets.atm.server.entity.Client clientEntity = clientCrudRepository.getClientByAccounts_Id(accountId).
                orElseThrow(ClientNotFoundException::new);
        return new Client(
                clientEntity.getName(),
                clientEntity.getCode(),
                clientEntity.getAge(),
                new Address(
                        clientEntity.getAddress_id().getStreet_id().getCity_id().getCountry_id().getCountry(),
                        clientEntity.getAddress_id().getStreet_id().getCity_id().getCity(),
                        clientEntity.getAddress_id().getStreet_id().getStreet(),
                        clientEntity.getAddress_id().getHouse(),
                        clientEntity.getAddress_id().getFlat()
                ),
                clientEntity.getAccounts().stream().map(
                        (github.marinets.atm.server.entity.Account account) -> (
                                new Account<Balance>(
                                        account.getNumber(),
                                        new BalanceCard(
                                                account.getBalance(),
                                                Currency.valueOf(account.getCurrency())
                                        ),
                                        account.getCards().stream().map(
                                                (github.marinets.atm.server.entity.Card cardInner) -> (
                                                        new Card(
                                                                cardInner.getNumber(),
                                                                cardInner.getPin(),
                                                                cardInner.getExpirationDate()
                                                        )
                                                )
                                        ).collect(Collectors.toList())
                                )
                        )
                ).collect(Collectors.toList())
        );
    }
}
