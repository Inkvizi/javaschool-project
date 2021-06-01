package github.marinets.atm.server.mappers;

import github.marinets.atm.common.dto.CardDTO;
import github.marinets.atm.server.domain.Card;
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

    public CardDTO toDTO(Card card) {
        return Objects.isNull(card) ? null : mapper.map(card, CardDTO.class);
    }
}
