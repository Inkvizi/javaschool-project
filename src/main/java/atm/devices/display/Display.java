package atm.devices.display;

import bank.client.Client;
import bank.services.operation.Balance;

public interface Display {
    void showWelcome();
    void showWelcomeClient(Client client);
    void showBalance(Balance balance);
    void showError(String errorText);
}
