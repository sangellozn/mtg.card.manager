package info.san.mtg.card.manager.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.StatsDto;
import info.san.mtg.card.manager.service.IStatsService;

@RestController
@RequestMapping("api/stats")
public class StatsController {
	
	private final IStatsService statsService;
	
	public StatsController(IStatsService statsService) {
		this.statsService = statsService;
	}

	@GetMapping("{uuid}")
	public StatsDto get(@PathVariable("uuid") String uuid) {
		return this.statsService.getStatsSet(uuid);
	}

}
