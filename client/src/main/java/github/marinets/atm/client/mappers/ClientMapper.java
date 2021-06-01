package github.marinets.atm.client.mappers;

import github.marinets.atm.client.domain.Client;
import github.marinets.atm.common.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ClientMapper {
    @Autowired
    private ModelMapper mapper;

    public Client toModel(ClientDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Client.class);
    }

    public ClientDTO toDTO(Client client) {
        return Objects.isNull(client) ? null : mapper.map(client, ClientDTO.class);
    }
}
