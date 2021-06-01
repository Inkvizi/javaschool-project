package github.marinets.atm.client.devices.display;

import github.marinets.atm.client.domain.Balance;
import github.marinets.atm.client.domain.Client;

import java.text.DecimalFormat;

public class DisplayLED implements Display {
    @Override
    public void showWelcome() {
        System.out.println("Добро пожаловать!");
    }

    @Override
    public void showWelcomeClient(Client client) {
        String welcome = "Здравствуйте, " + client.getName();
        System.out.println(welcome);
    }

    @Override
    public void showBalance(Balance balance) {
        DecimalFormat formatter = new DecimalFormat("##.00");
        String balanceMessage = "Ваш баланс на текущий момент: "
                + formatter.format(balance.getAmount()) + " "
                + balance.getCurrency();
        System.out.println(balanceMessage);
    }

    @Override
    public void showError(String errorText) {
        System.out.println(errorText);
    }
}
