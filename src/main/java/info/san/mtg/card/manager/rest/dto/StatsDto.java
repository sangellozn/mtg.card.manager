package info.san.mtg.card.manager.rest.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDto {
	
	private Collection<StatsSetDto> statsSet = new ArrayList<>();
	
	@Getter
	@Setter
	public static final class StatsSetDto {
		
		private String code;
		
		private String name;
		
		private int totalCards;
		
		private int possessedCards;
		
	}

}
