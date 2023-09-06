package info.san.mtg.card.manager.service;

import java.util.Collection;

import info.san.mtg.card.manager.rest.dto.model.AddCardDto;
import info.san.mtg.card.manager.rest.dto.model.UserDto;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto;

public interface IUserService {
	
	Collection<UserDto> findAll();
	
	void addSet(String userUuid, String setCode);
	
	void addCard(String userUuid, String cardUuid);
	
	void addCard(String userUuid, AddCardDto addCardDto);
	
	Collection<SetDto> findAllSets(String userUuid);

	UserSetDto getUserSet(String uuid, String code);

}
