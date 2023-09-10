package info.san.mtg.card.manager.rest.dto.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultDto {
	
	private Collection<SearchResultItemDto> results = new ArrayList<>();
	
	public enum ItemTypeEnum {
		
		SETS,
		
		CARDS
		
	}
	
	@Getter
	@Setter
	public static final class SearchResultItemDto {
		
		private ItemTypeEnum type;
		
		private String id;
		
		private String label;
		
		private String keyruneCode;
		
		private String urlImage;
		
	}
	
	public boolean addAll(Collection<SearchResultItemDto> items) {
		return this.results.addAll(items);
	}

}
