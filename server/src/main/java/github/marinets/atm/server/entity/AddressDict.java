package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Address_Dict")
@NoArgsConstructor
@Data
public class AddressDict {
    @Id
    @GeneratedValue
    private long id;

    private int house;

    private int flat;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private StreetDict street_id;
}
