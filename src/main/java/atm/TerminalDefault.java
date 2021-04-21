package atm;

import atm.card.CardData;
import atm.client.ClientData;
import atm.devices.cardreader.CardReader;
import atm.devices.display.Display;
import atm.devices.input.InputReader;
import atm.mappers.BalanceMapper;
import atm.mappers.CardMapper;
import atm.mappers.ClientMapper;
import bank.Processing;
import common.dto.BalanceDTO;
import common.dto.CardDTO;
import common.dto.ClientDTO;
import common.dto.Responce;

public class TerminalDefault implements Terminal {
    private final CardReader cardReader;
    private final Display display;
    private final InputReader input;
    private final Processing processing;
    private final CardMapper cardMapper;
    private final ClientMapper clientMapper;
    private final BalanceMapper balanceMapper;
    private boolean isVacant;
    private CardData card;
    private ClientData client;

    public TerminalDefault(CardReader cardReader, Display display, InputReader input) {
        this.cardReader = cardReader;
        this.display = display;
        this.input = input;
        this.processing = new Processing();
        this.cardMapper = new CardMapper();
        this.clientMapper = new ClientMapper();
        this.balanceMapper = new BalanceMapper();
        isVacant = true;
        display.showWelcome();
    }

    public boolean checkIsVacant() {
        return isVacant;
    }

    @Override
    public boolean startSession() {
        isVacant = false;

        if (!checkInitCard()) {
            stopInternal();
            return false;
        }

        if (!checkInitClient()) {
            stopInternal();
            return false;
        }

        display.showWelcomeClient(client);
        return true;
    }

    @Override
    public void stopSession() {
        stopInternal();
    }

    @Override
    public void showBalance() {
        Responce<BalanceDTO> balanceResponse = processing.getBalance(clientMapper.toDTO(client), cardMapper.toDTO(card));
        if (balanceResponse.isError()) {
            display.showError(balanceResponse.getErrorMessage());
        } else {
            display.showBalance(balanceMapper.toModel(balanceResponse.getResponseValue()));
        }
        stopInternal(); //возможно не нужно делать так как показываться баланс будет не долго
    }

    private void stopInternal() {
        cardReader.extractCard();
        client = null;
        display.showWelcome();
        isVacant = true;
    }

    private boolean checkInitCard() {
        String pinCode = input.readPin();
        String authenticationData = cardReader.readAuthenticationData();
        Responce<CardDTO> authenticationResponse = processing.decodeAuthenticationData(authenticationData, pinCode);
        if (authenticationResponse.isError()) {
            display.showError(authenticationResponse.getErrorMessage());
            return false;
        }
        CardDTO cardDTO = authenticationResponse.getResponseValue();
        card = cardMapper.toModel(cardDTO);
        return true;
    }

    private boolean checkInitClient() {
        Responce<ClientDTO> clientResponse = processing.getClientByCard(cardMapper.toDTO(card));
        if (clientResponse.isError()) {
            display.showError(clientResponse.getErrorMessage());
            return false;
        }
        client = clientMapper.toModel(clientResponse.getResponseValue());
        return true;
    }
}
