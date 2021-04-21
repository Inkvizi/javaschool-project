package bank.mappers;

import bank.services.operation.Balance;
import bank.services.operation.BalanceCard;
import common.dto.BalanceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BalanceMapper {

    @Autowired
    ModelMapper mapper;

    public Balance toModel(BalanceDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, BalanceCard.class);
    }

    public BalanceDTO toDTO(Balance balance) {
        return Objects.isNull(balance) ? null : mapper.map(balance, BalanceDTO.class);
    }
}
