package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
@NoArgsConstructor
@Data
public class CityDict {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Город не может быть больше 240 символов", max = 240)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryDict country_id;
}
