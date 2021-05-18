package atm.devices.cardreader;

public interface CardReader {
    String readAuthenticationData();
    void extractCard();
}
