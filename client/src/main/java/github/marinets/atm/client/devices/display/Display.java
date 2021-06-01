package github.marinets.atm.client.devices.display;

import github.marinets.atm.client.domain.Balance;
import github.marinets.atm.client.domain.Client;

public interface Display {
    void showWelcome();
    void showWelcomeClient(Client client);
    void showBalance(Balance balance);
    void showError(String errorText);
}
