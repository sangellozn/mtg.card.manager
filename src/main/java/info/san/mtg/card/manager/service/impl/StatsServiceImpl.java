package info.san.mtg.card.manager.service.impl;

import org.springframework.stereotype.Service;

import info.san.mtg.card.manager.repository.UserCardRepository;
import info.san.mtg.card.manager.rest.dto.StatsDto;
import info.san.mtg.card.manager.rest.dto.StatsDto.StatsSetDto;
import info.san.mtg.card.manager.service.IStatsService;

@Service
public class StatsServiceImpl implements IStatsService {
	
	private final UserCardRepository userCardRepository;
	
	public StatsServiceImpl(UserCardRepository userCardRepository) {
		this.userCardRepository = userCardRepository;
	}

	@Override
	public StatsDto getStatsSet(String userUuid) {
		StatsDto statsDto = new StatsDto();
		
		statsDto.getStatsSet().addAll(userCardRepository.findAllStatsByUserUuid(userUuid).stream().map(item -> {
			StatsSetDto dto = new StatsSetDto();
			
			dto.setCode(item.getCode());
			dto.setName(item.getSetName());
			dto.setPossessedCards(item.getPossessedCards());
			dto.setTotalCards(item.getTotalCards());
			
			return dto;
		}).toList());
		
		return statsDto;
	}
	
}
