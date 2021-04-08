package client;

public class ClientDataDefault implements ClientData {
    @Override
    public String getName() {
        return "clientName";
    }

    @Override
    public String getCode() {
        return "clientCode";
    }
}
