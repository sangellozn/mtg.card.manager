package info.san.mtg.card.manager.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.rest.dto.model.UserDto;

@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
	
	UserDto map(User user);
	
	Collection<UserDto> map(Collection<User> users);

}
