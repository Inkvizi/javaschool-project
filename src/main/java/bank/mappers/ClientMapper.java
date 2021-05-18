package bank.mappers;

import bank.client.Client;
import bank.client.ClientData;
import common.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class ClientMapper {
    @Autowired
    ModelMapper mapper;

    public Client toModel(ClientDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ClientData.class);
    }

    public ClientDTO toDTO(Client client) {
        return Objects.isNull(client) ? null : mapper.map(client, ClientDTO.class);
    }
}
