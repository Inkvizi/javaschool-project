package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CountryDict")
@NoArgsConstructor
@Data
public class CountryDict {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Страна не может быть больше 240 символов", max = 240)
    private String country;
}
