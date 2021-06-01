package github.marinets.atm.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {
    String country;
    String city;
    String street;
    int house;
    int flat;
}
