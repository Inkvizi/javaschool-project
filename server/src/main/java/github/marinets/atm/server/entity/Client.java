package github.marinets.atm.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Clients")
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @Size(message = "Имя клиента не может быть больше 240 символов", max = 240)
    private String name;

    @Size(message = "Код клиента не может быть больше 20 символов", max = 20)
    private String code;

    private int age;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressDict address_id;

    @OneToMany
    private Set<Account> accounts;
}
