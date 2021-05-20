package github.marinets.atm.server;

import github.marinets.atm.common.dto.*;
import github.marinets.atm.server.entity.card.Card;
import github.marinets.atm.server.entity.client.Account;
import github.marinets.atm.server.entity.client.Client;
import github.marinets.atm.server.mappers.BalanceMapper;
import github.marinets.atm.server.mappers.CardClientMapper;
import github.marinets.atm.server.mappers.ClientMapper;
import github.marinets.atm.common.authentication.Decoder;
import github.marinets.atm.common.utils.DateParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Processing {
    public static final String ERROR_READ_DATA = "Ошибка чтения карты";
    public static final String ERROR_CARD_IDENTIFICATION = "Карта не идентифицирована, обратитесь в отделение за помощью";
    public static final String ERROR_MULTIPLE_CLIENTS = "Ошибка обработки карты, обратитесь в отделение за помощью";
    public static final String ERROR_INCORRECT_PIN = "Введененный пинкод не верен";
    public static final String ERROR_EXPIRATION_DATE = "Срок дейтсвия карты истек, обратитесь в отделение для получения новой карты";
    public static final String ERROR_INCORRECT_PIN_FORMAT = "Неверный формат пинкода";
    public static final String ERROR_INCORRECT_CARD_FORMAT = "Неверный формат номера карты";
    private List<Client> clients;
    private final CardClientMapper cardClientMapper;
    private final ClientMapper clientMapper;
    private final BalanceMapper balanceMapper;

    public Processing () {
        cardClientMapper = new CardClientMapper();
        clientMapper = new ClientMapper();
        balanceMapper = new BalanceMapper();
    }

    public Responce<CardDTO> decodeAuthenticationData(String authenticationData, String pinCode) {
        if (pinCode.length() != 3) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN_FORMAT, null);
        }
        String[] requisites = Decoder.decodeCardData(authenticationData);
        if (requisites.length == 2) {
            if (requisites[0].length() != 16) {
                return new Responce<>(ResponceErrorCodes.ERROR_CARD_NUMBER, ERROR_INCORRECT_CARD_FORMAT, null);
            }
            LocalDate expirationDate = DateParser.parseStringToLocalDate(requisites[1]);
            CardDTO card = new CardDTO(requisites[0], expirationDate, pinCode);
            return new Responce<>(card);
        }
        return new Responce<>(ResponceErrorCodes.ERROR_READ_DATA, ERROR_READ_DATA, null);
    }

    public Responce<ClientDTO> getClientByCard(CardDTO cardDTO) {
        Card card = cardClientMapper.toModel(cardDTO);
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
        return new Responce<>(clientMapper.toDTO(filteredClient.get(0)));
    }

    public Responce<BalanceDTO> getBalance(ClientDTO clientDTO, CardDTO cardDTO) {
        Card card = cardClientMapper.toModel(cardDTO);
        Client client = clientMapper.toModel(clientDTO);
        if (!client.checkCardExpirationDate(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_EXPIRATION_DATE, ERROR_EXPIRATION_DATE, null);
        }
        Account account = client.getAccountByCard(card);
        BalanceDTO balanceDTO = balanceMapper.toDTO(account.getBalance());
        balanceDTO.setAccountNumber(account.getNumber());
        return new Responce<>(balanceDTO);
    }
}
