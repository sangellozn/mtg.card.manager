package info.san.mtg.card.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.Sets;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserSetMapper {
	
	@Mapping(target = "set", source = ".")
	UserSetDto map(Sets set);

}
