package github.marinets.atm.server;

import github.marinets.atm.common.dto.*;
import github.marinets.atm.server.domain.Account;
import github.marinets.atm.server.domain.Card;
import github.marinets.atm.server.domain.Client;
import github.marinets.atm.server.mappers.BalanceMapper;
import github.marinets.atm.server.mappers.CardMapper;
import github.marinets.atm.server.mappers.ClientMapper;
import github.marinets.atm.common.authentication.Decoder;
import github.marinets.atm.common.utils.DateParser;
import github.marinets.atm.server.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@AllArgsConstructor
public class Processing {
    public static final String ERROR_READ_DATA = "Ошибка чтения карты";
    public static final String ERROR_CARD_IDENTIFICATION = "Карта не идентифицирована, обратитесь в отделение за помощью";
    public static final String ERROR_INCORRECT_PIN = "Введененный пинкод не верен";
    public static final String ERROR_EXPIRATION_DATE = "Срок дейтсвия карты истек, обратитесь в отделение для получения новой карты";
    public static final String ERROR_INCORRECT_PIN_FORMAT = "Неверный формат пинкода";
    public static final String ERROR_INCORRECT_CARD_FORMAT = "Неверный формат номера карты";
    public static final String ERROR_INCORRECT_EXPIRATION_DATE_FORMAT = "Неверный формат даты срока окончания карты";

    private final CardMapper cardMapper;
    private final ClientMapper clientMapper;
    private final BalanceMapper balanceMapper;
    //services
    private final ClientService clientService;

    public Responce<CardDTO> decodeAuthenticationData(String authenticationData, String pinCode) {
        if (pinCode.length() != 4) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN_FORMAT, null);
        }
        String[] requisites = Decoder.decodeCardData(authenticationData);
        if (requisites.length == 2) {
            if (requisites[0].length() == 10) {
                try {
                    LocalDate expirationDate = DateParser.parseStringToLocalDate(requisites[1]);
                    CardDTO card = new CardDTO(requisites[0], expirationDate, pinCode);
                    return new Responce<>(card);
                } catch (DateTimeParseException e) {
                    return new Responce<>(ResponceErrorCodes.ERROR_EXPIRATION_DATE_FORMAT, ERROR_INCORRECT_EXPIRATION_DATE_FORMAT, null);
                }
            } else {
                return new Responce<>(ResponceErrorCodes.ERROR_CARD_NUMBER, ERROR_INCORRECT_CARD_FORMAT, null);
            }
        }
        return new Responce<>(ResponceErrorCodes.ERROR_READ_DATA, ERROR_READ_DATA, null);
    }

    public Responce<ClientDTO> getClientByCard(CardDTO cardDTO) {
        Card card = cardMapper.toModel(cardDTO);
        Client client = clientService.getClientByCard(card);

        if (client == null) {
            return new Responce<>(ResponceErrorCodes.ERROR_NO_DATA, ERROR_CARD_IDENTIFICATION, null);
        }

        if (!client.checkCardPin(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN, null);
        }

        return new Responce<>(clientMapper.toDTO(client));
    }

    public Responce<BalanceDTO> getBalance(CardDTO cardDTO) {
        Card card = cardMapper.toModel(cardDTO);
        Client client = clientService.getClientByCard(card);
        if (!client.checkCardExpirationDate(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_EXPIRATION_DATE, ERROR_EXPIRATION_DATE, null);
        }
        if (!client.checkCardPin(card)) {
            return new Responce<>(ResponceErrorCodes.ERROR_PINCODE_INCORRECT, ERROR_INCORRECT_PIN, null);
        }
        Account account = client.getAccountByCard(card);
        BalanceDTO balanceDTO = balanceMapper.toDTO(account.getBalance());
        balanceDTO.setAccountNumber(account.getNumber());
        return new Responce<>(balanceDTO);
    }
}
