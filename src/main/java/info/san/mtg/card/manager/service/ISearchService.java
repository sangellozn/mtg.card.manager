package info.san.mtg.card.manager.service;

import info.san.mtg.card.manager.rest.dto.model.SearchResultDto;

public interface ISearchService {
	
	SearchResultDto search(String query);

}
