package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AddressDict")
@NoArgsConstructor
@Data
public class AddressDict {
    @Id
    @GeneratedValue
    private long id;

    private Long house;

    private Long flat;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private StreetDict street_id;
}
