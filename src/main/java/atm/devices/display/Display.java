package atm.devices.display;

import atm.balance.BalanceData;
import atm.client.ClientData;

public interface Display {
    void showWelcome();
    void showWelcomeClient(ClientData client);
    void showBalance(BalanceData balance);
    void showError(String errorText);
}
