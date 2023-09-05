package info.san.mtg.card.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.Sets;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SetMapper {
	
	SetDto map(Sets set);

}
