package github.marinets.atm.server.services;

import github.marinets.atm.server.domain.Card;
import github.marinets.atm.server.exception.AccountNotFoundException;
import github.marinets.atm.server.repository.AccountCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    AccountCrudRepository accountCrudRepository;
    CardService cardService;

    Long getAccountIDByCard(Card card) {
        long cardId = cardService.getCardId(card);
        return accountCrudRepository.findByCards_Id(cardId).orElseThrow(AccountNotFoundException::new).getId();
    }
}
