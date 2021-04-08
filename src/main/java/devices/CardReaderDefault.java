package devices;

import authentication.Card;
import authentication.Decoder;
import client.ClientData;
import client.ClientDataDefault;

import java.util.Optional;

import static java.util.Optional.of;

public class CardReaderDefault implements CardReader {
    private ClientData client; //не уверен, что стоит тут хранить, разве что не хранить его больше нигде и получать отсюда

    @Override
    public Optional<ClientData> inputCard(Card card) {
        String secretData = card.getAuthenticationData();
        //декодируем и составляем объект клиента
        String decodedData = Decoder.decode(secretData);
        client = new ClientDataDefault();
        return Optional.of(client);
    }

    @Override
    public void extractCard() {
        //вытаскиваем карту и зачищаем данные
        client = null;
    }
}
