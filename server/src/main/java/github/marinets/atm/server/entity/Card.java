package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Cards")
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Номер карты должен быть 10 смиволов", min = 10, max = 10)
    private String number;

    @Size(message = "Пинкод карты должен быть 4 смивола", min = 4, max = 4)
    private String pin;
}
