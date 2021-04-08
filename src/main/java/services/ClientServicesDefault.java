package services;

import client.ClientData;
import services.operation.Balance;
import services.operation.BalanceCard;
import services.operation.Currency;

import java.math.BigDecimal;

public class ClientServicesDefault implements ClientServices {
    @Override
    public Balance getClientBalance(ClientData client) {
        String clientCode = client.getCode();
        Balance balance = new BalanceCard(BigDecimal.valueOf(0), Currency.RUR);
        //получаем или заполняем
        return balance;
    }
}
