package github.marinets.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO {
    String number;
    LocalDate expirationDate;
    String pinCode;
}
