package devices;

import authentication.Card;
import client.ClientData;
import client.ClientDataDefault;

public class CardReaderDefault implements CardReader {
    private ClientData client; //не уверен, что стоит тут хранить, разве что не хранить его больше нигде и получать отсюда

    @Override
    public ClientData inputCard(Card card) {
        String secretData = card.getAuthenticationData();
        //декодируем и составляем объект клиента
        client = new ClientDataDefault();
        return client;
    }

    @Override
    public void extractCard() {
        //вытаскиваем карту и зачищаем данные
        client = null;
    }
}
