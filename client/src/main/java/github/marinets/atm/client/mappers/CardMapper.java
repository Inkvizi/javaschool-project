package github.marinets.atm.client.mappers;

import github.marinets.atm.client.domain.Card;
import github.marinets.atm.common.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CardMapper {
    @Autowired
    private ModelMapper mapper;

    public Card toModel(CardDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Card.class);
    }

    public CardDTO toDTO(Card cardData) {
        return Objects.isNull(cardData) ? null : mapper.map(cardData, CardDTO.class);
    }
}
