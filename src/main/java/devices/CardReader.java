package devices;

import authentication.Card;
import client.ClientData;

import java.util.Optional;

public interface CardReader {
    Optional<ClientData> inputCard(Card card);
    void extractCard();
}
