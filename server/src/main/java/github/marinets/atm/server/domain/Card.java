package github.marinets.atm.server.domain;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Card {
    private final int PIN_CODE_MAX_LENGTH = 4;
    private String number;
    private String pinCode;
    private LocalDate expirationDate;

    public boolean CheckPinCode(String pinCode) {
        return (pinCode.length() == PIN_CODE_MAX_LENGTH) && (this.getPinCode().equals(pinCode));
    }

    public boolean CheckExpirationDate() {
        return (expirationDate.isAfter(LocalDate.now()) || (expirationDate.isEqual(LocalDate.now())));
    }
}
