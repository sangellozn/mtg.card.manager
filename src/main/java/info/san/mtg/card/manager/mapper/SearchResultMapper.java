package info.san.mtg.card.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.projection.SearchResultProjection;
import info.san.mtg.card.manager.rest.dto.model.SearchResultDto.SearchResultItemDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SearchResultMapper {
	
	SearchResultItemDto map(SearchResultProjection projection);

}
