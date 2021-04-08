package devices;

import authentication.Card;
import client.ClientData;

public interface CardReader {
    ClientData inputCard(Card card);
    void extractCard();
}
