package info.san.mtg.card.manager.service;

import info.san.mtg.card.manager.rest.dto.StatsDto;

public interface IStatsService {
	
	StatsDto getStatsSet(String userUuid);

}
