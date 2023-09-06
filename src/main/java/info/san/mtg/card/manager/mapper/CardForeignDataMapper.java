package info.san.mtg.card.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.CardForeignData;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto.CardDto.CardForeignDataDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CardForeignDataMapper {
	
	CardForeignDataDto map(CardForeignData cardForeignData);

}
