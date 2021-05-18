package bank.mappers;

import bank.card.CardClient;
import common.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardClientMapper {
    @Autowired
    private ModelMapper mapper;

    public CardClient toModel(CardDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, CardClient.class);
    }

    public CardDTO toDTO(CardClient card) {
        return Objects.isNull(card) ? null : mapper.map(card, CardDTO.class);
    }
}
