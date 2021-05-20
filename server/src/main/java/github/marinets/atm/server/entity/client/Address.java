package github.marinets.atm.server.entity.client;

public interface Address {
    String getCountry();
    String getCity();
    String getStreet();
    int getHouse();
    int getFlat();
}
