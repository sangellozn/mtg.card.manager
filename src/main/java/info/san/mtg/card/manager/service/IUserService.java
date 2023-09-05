package info.san.mtg.card.manager.service;

import java.util.Collection;

import info.san.mtg.card.manager.rest.dto.model.UserDto;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;

public interface IUserService {
	
	Collection<UserDto> findAll();
	
	void addSet(String userUuid, String setCode);
	
	void addCard(String userUuid, String cardUuid);
	
	Collection<SetDto> findAllSets(String userUuid);

}
