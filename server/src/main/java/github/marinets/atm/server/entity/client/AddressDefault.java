package github.marinets.atm.server.entity.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressDefault implements Address {
    String country;
    String city;
    String street;
    int house;
    int flat;
}
