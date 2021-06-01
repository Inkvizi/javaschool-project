package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Номер счета должен быть 16 смиволов", min = 16, max = 16)
    private String number;

    private BigDecimal balance;

    @Size(message = "Валюта счета должен быть 3 смивола", min = 3, max = 3)
    private String currency;

    @OneToMany
    private Set<Card> cards;
}
