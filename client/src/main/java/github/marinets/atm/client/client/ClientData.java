package github.marinets.atm.client.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientData {
    private String name;
    private String code;
    private int age;
}
