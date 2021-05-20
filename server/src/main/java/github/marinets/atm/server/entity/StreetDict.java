package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "StreetDict")
@NoArgsConstructor
@Data
public class StreetDict {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Улица не может содержать больше 240 символов", max = 240)
    private String street;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityDict city_id;
}
