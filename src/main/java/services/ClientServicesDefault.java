package services;

import client.ClientData;
import services.operation.Balance;
import services.operation.BalanceCard;

public class ClientServicesDefault implements ClientServices {
    @Override
    public Balance getClientBalance(ClientData client) {
        String clientCode = client.getClientCode();
        Balance balance = new BalanceCard(0, "RUR");
        //получаем или заполняем
        return balance;
    }
}
