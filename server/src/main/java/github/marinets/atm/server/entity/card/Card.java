package github.marinets.atm.server.entity.card;

import java.time.LocalDate;

public interface Card {
    String getNumber();
    LocalDate getExpirationDate();
    String getPinCode();

    boolean CheckPinCode();
    boolean CheckExpirationDate();
}
