package github.marinets.atm.client.services;

import github.marinets.atm.client.domain.Card;
import github.marinets.atm.client.domain.Client;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class TerminalService {

    private String sessionId = "";
    private Card card;
    private Client client;

    public boolean checkIsVacant(String sessionId){
        return this.sessionId.isEmpty() || this.sessionId.equals(sessionId);
    }

    public String startSession(Card card, Client client) {
        this.card = card;
        this.client = client;
        sessionId = "97ff764f-8b5f-4661-8e32-511a52595c2e"; //UUID.randomUUID().toString();

        return sessionId;
    }

    public Optional<Card> loadCardBySession(String sessionId) {
        if (this.sessionId.equals((sessionId))) {
            return Optional.of(card);
        }
        return Optional.empty();
    }

    public  void stopSession (String sessionId) {
        if (this.sessionId.equals(sessionId)) {
            this.sessionId = "";
            client = null;
            card = null;
        }
    }
}
