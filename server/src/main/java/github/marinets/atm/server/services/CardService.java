package github.marinets.atm.server.services;

import github.marinets.atm.server.domain.Card;
import github.marinets.atm.server.exception.CardNotFoundException;
import github.marinets.atm.server.repository.CardCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    CardCrudRepository cardCrudRepository;

    Long getCardId(Card card) {
        return cardCrudRepository.getCardByNumber(card.getNumber()).orElseThrow(CardNotFoundException::new).getId();
    }
}
