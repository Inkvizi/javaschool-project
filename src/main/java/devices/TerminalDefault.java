package devices;

import authentication.Card;
import client.ClientData;
import services.ClientServices;
import services.ClientServicesDefault;
import services.operation.Balance;

import java.util.Optional;

public class TerminalDefault implements Terminal{
    private final CardReader cardReader;
    private final Display display;
    private final ClientServices services;
    private boolean isVacant;
    private ClientData client;

    public TerminalDefault(CardReader cardReader, Display display) {
        this.cardReader = cardReader;
        this.display = display;
        services = new ClientServicesDefault();
        isVacant = true;
        display.showWelcome();
    }

    public boolean checkIsVacant() {
        return isVacant;
    }

    @Override
    public void startSession(Card card) {
        Optional<ClientData> clientDataOptional = cardReader.inputCard(card);
        if (clientDataOptional.isPresent()) {
            client = clientDataOptional.get();
            display.showWelcomeClient(client);
            isVacant = false;
        }
    }

    @Override
    public void stopSession() {
        stopInternal();
    }

    @Override
    public void showBalance() {
        Balance balance = services.getClientBalance(client);
        display.showBalance(balance);
        stopInternal(); //возможно не нужно делать так как показываться баланс будет не долго
    }

    private void stopInternal() {
        cardReader.extractCard();
        client = null;
        display.showWelcome();
        isVacant = true;
    }
}
