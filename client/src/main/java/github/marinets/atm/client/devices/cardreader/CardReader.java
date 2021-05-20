package github.marinets.atm.client.devices.cardreader;

public interface CardReader {
    String readAuthenticationData();
    void extractCard();
}
