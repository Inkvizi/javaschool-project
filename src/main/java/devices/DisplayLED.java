package devices;

import client.ClientData;
import services.operation.Balance;

import java.text.DecimalFormat;

public class DisplayLED implements Display {
    @Override
    public void showWelcome() {
        //показываем экран приветствия
    }

    @Override
    public void showWelcomeClient(ClientData client) {
        String welcome = "Здравствуйте, " + client.getName();
        //показываем экран приветствия для клиента
    }

    @Override
    public void showBalance(Balance balance) {
        DecimalFormat formatter = new DecimalFormat("##.00");
        String balanceMessage = "Ваш баланс на текущий момент: "
                + formatter.format(balance.getAmount()) + " "
                + balance.getCurrency();
    }
}
