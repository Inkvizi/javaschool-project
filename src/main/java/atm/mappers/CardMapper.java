package atm.mappers;

import atm.card.CardData;
import common.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardMapper {
    @Autowired
    private ModelMapper mapper;

    public CardData toModel(CardDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, CardData.class);
    }

    public CardDTO toDTO(CardData cardData) {
        return Objects.isNull(cardData) ? null : mapper.map(cardData, CardDTO.class);
    }
}
