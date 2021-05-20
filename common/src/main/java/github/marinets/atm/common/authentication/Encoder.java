package github.marinets.atm.common.authentication;

import java.time.LocalDate;

public class Encoder {
    public static String EncodeCardData(String cardNumber, LocalDate expirationDate) {
        return cardNumber + ";" + expirationDate.toString();
    }
}
