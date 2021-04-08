package services;

import client.ClientData;
import services.operation.Balance;

public interface ClientServices {
    Balance getClientBalance(ClientData client);
}
