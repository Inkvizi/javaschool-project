package devices;

import client.ClientData;
import services.operation.Balance;

public interface Display {
    void showWelcome();
    void showWelcomeClient(ClientData client);
    void showBalance(Balance balance);
}
