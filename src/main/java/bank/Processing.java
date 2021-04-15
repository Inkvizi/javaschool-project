package bank;

import bank.card.Card;
import bank.card.CardClient;
import common.authentication.Decoder;
import bank.client.Client;
import common.dto.Responce;
import common.dto.ResponceErrorCodes;
import bank.services.operation.Balance;
import common.utils.DateParser;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class Processing {
    public static final String ERROR_READ_DATA = "Ошибка чтения карты";
    public static final String ERROR_CARD_IDENTIFICATION = "Карта не идентифицирована, обратитесь в отделение за помощью";
    public static final String ERROR_MULTIPLE_CLIENTS = "Ошибка обработки карты, обратитесь в отделение за помощью";
    public static final String ERROR_INCORRECT_PIN = "Введененный пинкод не верен";
    public static final String ERROR_EXPIRATION_DATE = "Срок дейтсвия карты истек, обратитесь в отделение для получения новой карты";
    public static final String ERROR_INCORRECT_PIN_FORMAT = "Неверный формат пинкода";
    public static final String ERROR_INCORRECT_CARD_FORMAT = "Неверный формат номера карты";
    private List<Client> clients;

    public Responce<Card> decodeAuthenticationData(String authenticationData, String pinCode) {
        if (pinCode.length() != 3) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN_FORMAT, null);
        }
        String[] requisites = Decoder.decodeCardData(authenticationData);
        if (requisites.length == 2) {
            if (requisites[0].length() != 16) {
                return new Responce<>(ResponceErrorCodes.ERROR_CARD_NUMBER, ERROR_INCORRECT_CARD_FORMAT, null);
            }
            LocalDate expirationDate = DateParser.parseStringToLocalDate(requisites[1]);
            Card card = new CardClient(requisites[0], pinCode, expirationDate);
            return new Responce<>(card);
        }
        return new Responce<>(ResponceErrorCodes.ERROR_READ_DATA, ERROR_READ_DATA, null);
    }

    public Responce<Client> getClientByCard(Card card) {
        List<Client> filteredClient = clients.stream().filter(client -> client.hasCard(card)).collect(Collectors.toList());
        if (filteredClient.size() == 0) {
            return new Responce<>(ResponceErrorCodes.ERROR_NO_DATA, ERROR_CARD_IDENTIFICATION, null);
        }
        if (filteredClient.size() > 1) {
            return new Responce<>(ResponceErrorCodes.ERROR_MULTIPLE_DATA, ERROR_MULTIPLE_CLIENTS, null);
        }
        Client client = filteredClient.get(0);
        if (!client.checkCardPin(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN, null);
        }
        return new Responce<>(filteredClient.get(0));
    }

    public Responce<Balance> getBalance(Client client, Card card) {
        if (!client.checkCardExpirationDate(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_EXPIRATION_DATE, ERROR_EXPIRATION_DATE, null);
        }
        return new Responce<>(client.getBalance(card));
    }
}
