package atm;

import bank.card.Card;
import bank.Processing;
import bank.client.Client;
import atm.devices.cardreader.CardReader;
import atm.devices.display.Display;
import atm.devices.input.InputReader;
import common.dto.Responce;
import bank.services.operation.Balance;

public class TerminalDefault implements Terminal {
    private final CardReader cardReader;
    private final Display display;
    private final InputReader input;
    private final Processing processing;
    private boolean isVacant;
    private Card card;
    private Client client;

    public TerminalDefault(CardReader cardReader, Display display, InputReader input) {
        this.cardReader = cardReader;
        this.display = display;
        this.input = input;
        this.processing = new Processing();
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
        Responce<Balance> balanceResponce = processing.getBalance(client, card);
        if (balanceResponce.isError()) {
            display.showError(balanceResponce.getErrorMessage());
        } else {
            display.showBalance(balanceResponce.getResponseValue());
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
        Responce<Card> authenticationResponce = processing.decodeAuthenticationData(authenticationData, pinCode);
        if (authenticationResponce.isError()) {
            display.showError(authenticationResponce.getErrorMessage());
            return false;
        }
        card = authenticationResponce.getResponseValue();
        return true;
    }

    private boolean checkInitClient() {
        Responce<Client> clientResponce = processing.getClientByCard(card);
        if (clientResponce.isError()) {
            display.showError(clientResponce.getErrorMessage());
            return false;
        }
        client = clientResponce.getResponseValue();
        return true;
    }
}
