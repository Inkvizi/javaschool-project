package atm.mappers;

import atm.client.ClientData;
import common.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientMapper {
    @Autowired
    private ModelMapper mapper;

    public ClientData toModel(ClientDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ClientData.class);
    }

    public ClientDTO toDTO(ClientData client) {
        return Objects.isNull(client) ? null : mapper.map(client, ClientDTO.class);
    }
}
