package github.marinets.atm.client.mappers;

import github.marinets.atm.client.domain.Balance;
import github.marinets.atm.common.dto.BalanceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class BalanceMapper {
    @Autowired
    ModelMapper mapper;
    public Balance toModel(BalanceDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Balance.class);
    }
    public BalanceDTO toDTO(Balance balance) {
        return Objects.isNull(balance) ? null : mapper.map(balance, BalanceDTO.class);
    }
}
