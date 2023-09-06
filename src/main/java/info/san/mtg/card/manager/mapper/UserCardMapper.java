package info.san.mtg.card.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.UserCard;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto.CardDto.UserCardDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserCardMapper {

	UserCardDto map(UserCard userCard);
	
}
