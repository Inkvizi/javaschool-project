package github.marinets.atm.client.devices.display;

import github.marinets.atm.client.balance.BalanceData;
import github.marinets.atm.client.client.ClientData;

public interface Display {
    void showWelcome();
    void showWelcomeClient(ClientData client);
    void showBalance(BalanceData balance);
    void showError(String errorText);
}
