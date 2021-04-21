package atm.mappers;

import atm.balance.BalanceData;
import common.dto.BalanceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BalanceMapper {
    @Autowired
    ModelMapper mapper;
    public BalanceData toModel(BalanceDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, BalanceData.class);
    }
    public BalanceDTO toDTO(BalanceData balance) {
        return Objects.isNull(balance) ? null : mapper.map(balance, BalanceDTO.class);
    }
}
